package com.example.stock.service;

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
class StockServiceTest {
    @Autowired
    private PessimisticLockStockService stockService;

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


    @DisplayName("재고감소")
    @Test
    void 재고감소() {
        //given, when
        stockService.decrease(1l, 1L);

        Stock stock = stockRepository.findById(1L).orElseThrow();

        //then
        assertEquals(99, stock.getQuantity());
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
                try{
                    stockService.decrease(1L, 1L);
                }finally {
                    countDownLatch.countDown();

                }
            });
        }

        countDownLatch.await();

        Stock stock = stockRepository.findById(1L).orElseThrow();

        //then
        assertEquals(0, stock.getQuantity());

    }

}
