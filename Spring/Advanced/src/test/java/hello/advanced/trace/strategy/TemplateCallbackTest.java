package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.Callback;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {


    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("CallBack 로직 수행");
            }
        });
    }

    @Test
    void callBackV2() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> {
            log.info("콜백 비즈니스 로직 수행 BY 람다");
        });

    }
}
