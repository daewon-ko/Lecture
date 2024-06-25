package sample.cafekiosk_review.spring.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import sample.cafekiosk_review.spring.api.service.product.response.ProductResponse;

@Getter
public class APiResponse<T> {
    private int code;
    private HttpStatus status;
    private String message;
    private T data;

    public APiResponse(final HttpStatus status, final String message, final T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> APiResponse<T> of(HttpStatus status, String message, T data) {
        return new APiResponse<>(status, message, data);
    }

    public static <T> APiResponse<T> of(HttpStatus status, T data) {
        return of(status, status.name(), data);
    }

    //200을 반환하는 상황이 많을 때 생성자
    public static <T> APiResponse<T> ok(T data) {
        return of(HttpStatus.OK, data);
    }
}
