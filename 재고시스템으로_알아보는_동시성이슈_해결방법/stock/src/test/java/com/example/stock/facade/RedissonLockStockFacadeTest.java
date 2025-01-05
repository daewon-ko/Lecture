package com.example.stock.facade;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedissonLockStockFacadeTest {
    @Autowired
    private RedissonLockStockFacade redissonLockStockFacade;

    @Autowired
    private StockRepository stockRepository;


    @BeforeEach
    public void before() {
        stockRepository.saveAndFlush(new Stock(1L, 100L));
    }

    @AfterEach
    public void teatDown() {
        stockRepository.deleteAllInBatch();
    }


    @DisplayName("동시에 여러 개의 요청")
    @Test
    void test() throws InterruptedException {
        //given
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        //when

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {

                try {
                    redissonLockStockFacade.decrease(1L, 1L);


                } finally {
                    countDownLatch.countDown();

                }
            });

        }


        countDownLatch.await();

        Stock stock = stockRepository.findById(1L).orElseThrow();

        //then
        assertEquals(0, stock.getQuantity());

    }


    @DisplayName("동시에 여러 개의 요청")
    @Test
    void test2() throws InterruptedException {
        // given
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    redissonLockStockFacade.decrease(1L, 1L);
                } finally {
                    countDownLatch.countDown(); // 작업 종료 후 countDown
                }
            });
        }

        countDownLatch.await(); // 모든 스레드의 작업이 종료될 때까지 대기

        Stock stock = stockRepository.findById(1L).orElseThrow();

        // then
        assertEquals(0, stock.getQuantity());
    }
}

