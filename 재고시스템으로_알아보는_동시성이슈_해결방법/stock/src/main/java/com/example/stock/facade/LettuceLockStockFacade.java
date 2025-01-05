package com.example.stock.facade;

import com.example.stock.repository.RedisLockRepository;
import com.example.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {
    private final RedisLockRepository repository;
    private final StockService stockService;
    private final RedisLockRepository redisLockRepository;

    public LettuceLockStockFacade(RedisLockRepository repository, StockService stockService, RedisLockRepository redisLockRepository) {
        this.repository = repository;
        this.stockService = stockService;
        this.redisLockRepository = redisLockRepository;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        // Redis Lock을 획득할 때까지 대기
        while (!redisLockRepository.lock(id)) {
            // Redis 부하 줄이기 위해 잠시 대기
            Thread.sleep(100);
        }

        try {
            stockService.decrease(id, quantity);
        } finally {
            redisLockRepository.unlock(id);
        }


    }
}
