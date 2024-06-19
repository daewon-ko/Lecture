package sample.cafekiosk_review.spring.domain.api.service.order.request;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateRequest {
    private List<String> productNumbers;

    @Builder

    private OrderCreateRequest(final List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
