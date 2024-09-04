package hello.springtx.exception;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RollbackTest {

    @Autowired
    RollbackService rollbackService;

    @DisplayName("")
    @Test
    void runtimeException() {

        //given
        assertThatThrownBy(
                () -> rollbackService.runtimeException()
        ).isInstanceOf(RuntimeException.class);

        //when

        //then


    }

    @DisplayName("")
    @Test
    void checkedException() {

        //given
        assertThatThrownBy(
                () -> rollbackService.checkedException()
        ).isInstanceOf(MyException.class);

        //when

        //then

    }

    @DisplayName("")
    @Test
    void rollbackFor() {

        //given
        assertThatThrownBy(
                () -> rollbackService.rollbackFor()
        ).isInstanceOf(MyException.class);


        //when

        //then

    }




    @TestConfiguration
    static class RollBackConfig{
        @Bean
        RollbackService rollbackService(){
            return new RollbackService();
        }


    }


    @Slf4j
    static class RollbackService{
        //런타임 예외발생 : 롤백
        @Transactional
        public void runtimeException() {
            log.info("call runtimeException");
            throw new RuntimeException();
        }

        // 체크 예외 발생 : 커밋
        @Transactional
        public void checkedException() throws MyException {
            log.info("call checkedException");
            throw new MyException();
        }

        // 체크예외 발생 : 롤백
        @Transactional(rollbackFor = MyException.class)
        public void rollbackFor() throws MyException {
            log.info("call rollbackFor");
            throw new MyException();
        }
    }

    static class MyException extends Exception{

    }

}
