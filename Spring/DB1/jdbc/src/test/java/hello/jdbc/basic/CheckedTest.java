package hello.jdbc.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class CheckedTest {

    @DisplayName("")
    @Test
    void checked_catch() {

        //given
        Service service = new Service();
        service.callCatch();

        //when

        //then

    }



    @DisplayName("")
    @Test
    void checked_throw() {

        //given
        Service service = new Service();
        assertThatThrownBy(
                () -> service.callThrow()).isInstanceOf(MyCheckedException.class);


        //when

        //then

    }



    static class Service {
        Repository repository = new Repository();

        public void callCatch() {

            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외 처리, message = {}", e.getMessage(), e);
            }


        }

        public void callThrow () throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }

    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }


}
