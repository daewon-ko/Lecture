package shppingmall.api.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shppingmall.api.repository.CouponRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;

    @DisplayName("")
    @Test
    void 한번만응모() {
        //given
        //when

        applyService.apply(1L);

        long count = couponRepository.count();


        //then

        Assertions.assertThat(count).isEqualTo(1);

    }


    @DisplayName("")
    @Test
    void 여러명응모() throws InterruptedException {
        //given

        int threadCount  = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.apply(userId);
                } finally {
                    countDownLatch.countDown();
                }
            });

        }

        countDownLatch.await();

        long count = couponRepository.count();


        //then
        Assertions.assertThat(count).isEqualTo(100);

    }


}