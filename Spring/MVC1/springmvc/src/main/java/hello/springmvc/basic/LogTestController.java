package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j  // 롬복 제공 어노테이션.해당 어노테이션 작성시 logger 클래스 import 필요없이 로깅 확인 가능
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        // 로그를 사용하지 않아도 a+b 계산 로지이 먼저 실행됨 이런 방식으로 사용하면 x?
        log.debug("String concat log=" + name);
        return "ok";
    }
}
