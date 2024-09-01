package hello.jdbc.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Slf4j
public class UncheckedAppTest {

    @DisplayName("")
    @Test
    void unchecked() {

        //given
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(
                () -> controller.request()
        ).isInstanceOf(Exception.class);

        //when

        //then

    }

    @DisplayName("")
    @Test
    void printEx() {

        //given
        Controller controller = new Controller();
        try {
            controller.request();
        } catch (Exception e) {
            log.info("ex", e);
        }

        //when

        //then

    }



    static class Controller{
        Service service = new Service();

        public void request() {
            service.logic();
        }

    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }
    }

    static class Repository {
        public void call() {
            try {
                runSQL();
            } catch (SQLException e) {
                throw new RunTimeSQLException(e);
            }


        }

        private void runSQL() throws SQLException {
            throw new SQLException("ex");
        }
    }

    static class NetworkClient {
        public void call() {
            throw new RuntimeConnectionException("연결 실패");
        }
    }

    static class RuntimeConnectionException extends RuntimeException {
        public RuntimeConnectionException(String message) {
            super(message);
        }
    }

    static class RunTimeSQLException extends RuntimeException {
        public RunTimeSQLException(Throwable cause) {
            super(cause);
        }

        public RunTimeSQLException() {
        }
    }

}
