package hello.springtx.order;


public class NotEnoughException extends Exception {

    public NotEnoughException(String message) {
        super(message);
    }
}