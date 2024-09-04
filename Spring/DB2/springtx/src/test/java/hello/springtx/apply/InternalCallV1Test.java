package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
@Slf4j
public class InternalCallV1Test {
    @Autowired
    private CallService callService;


    @DisplayName("")
    @Test
    void printProxy() {

        //given
        log.info("callService Internal = {}", callService.getClass());

        //when

        //then

    }

    @DisplayName("")
    @Test
    void internalCall() {

        //given
        callService.internal();
        //when

        //then

    }

    @DisplayName("")
    @Test
    void externalCall() {

        //given
        callService.external();


        //when

        //then

    }




    @TestConfiguration
    static class InternalCallV1Config {
        @Bean
        CallService callService() {
            return new CallService();
        }
    }

    @Slf4j
    static class CallService{

        public void external() {
            log.info("call external");
            printTxInfo();
            internal();
        }
        @Transactional
        public void internal() {
            log.info("call internal");
            printTxInfo();
        }
        private void printTxInfo() {
            boolean txActive =
                    TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
        }

    }


}
