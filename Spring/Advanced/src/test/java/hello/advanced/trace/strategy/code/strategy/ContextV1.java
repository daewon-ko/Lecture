package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {
    private Strategy strategy;

    public ContextV1(final Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        // 변하지 않는 것 실행
        long startTime = System.currentTimeMillis();
        strategy.call();    // 변하는 부분 - 비즈니스 로직
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
