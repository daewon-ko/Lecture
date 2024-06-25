package sample.cafekiosk_review.spring.api.service.order.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderCreateServiceRequest {
    /**
     * 모듈 분리시 servic Layer에서 검증이 필요가 없어진다.
     */
//    @NotEmpty(message = "상품 번호 리스트는 필수입니다.")
    private List<String> productNumbers;

    @Builder
    private OrderCreateServiceRequest(final List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
