package sample.cafekiosk_review.spring.api.controller.product.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk_review.spring.domain.product.Product;
import sample.cafekiosk_review.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk_review.spring.domain.product.ProductType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@NoArgsConstructor
public class ProductCreateRequest {
    @NotNull
    private ProductType type;

    @NotNull
    private ProductSellingStatus sellingStatus;

    @NotBlank
    private String name;

    @Positive
    private int price;

    @Builder
    private ProductCreateRequest(final ProductType type, final ProductSellingStatus sellingStatus, final String name, final int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(final String nextProductNumber) {
        return Product.builder()
                .productNumber(nextProductNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }
}