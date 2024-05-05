package hello.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {
    private Component component;

    public TimeDecorator(final Component component) {
        this.component = component;

    }

    @Override
    public String operation() {
        log.info("시작시간 재는 Operation 수행");
        Long startTime = System.currentTimeMillis();
        String result = component.operation();

        Long endTime = System.currentTimeMillis();
        log.info("수행시간 = {}", endTime - startTime);
        return  result;
    }
}




