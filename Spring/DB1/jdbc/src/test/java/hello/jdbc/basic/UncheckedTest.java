package hello.jdbc.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class UncheckedTest {

    @DisplayName("")
    @Test
    void unchecked_catch() {

        //given
        Service service = new Service();
        service.callCatch();

        //when

        //then

    }


    @DisplayName("")
    @Test
    void unchecked_throw() {

        //given
        Service service = new Service();
        assertThatThrownBy(
                () -> service.callThrow()
        ).isInstanceOf(MyUncheckedException.class);

        //when

        //then

    }


    static class Service {
        Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("예외처리, message = {}", e.getMessage(), e);
            }
        }

        public void callThrow() {
            repository.call();
        }
    }

    static class Repository {
        public void call() {
            throw new MyUncheckedException("ex");
        }
    }


    static class MyUncheckedException extends RuntimeException {

        public MyUncheckedException(String message) {
            super(message);
        }
    }
}
