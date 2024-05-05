package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void setThreadLocalService() {
        log.info("main Start");
        Runnable userA = () -> {
            threadLocalService.logic("user A");
        };

        Runnable userB = () -> {
            threadLocalService.logic("user B");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread -A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread -B");

        threadA.start();
        sleep(100);
        threadB.start();

        sleep(2000);
        log.info("main exit");

    }

    private void sleep(final int milliTime) {
        try {
            Thread.sleep(milliTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
