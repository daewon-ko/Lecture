package sample.cafekiosk_review.spring.domain.api.service.order.response;


import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk_review.spring.domain.api.service.product.response.ProductResponse;
import sample.cafekiosk_review.spring.domain.order.Order;
import sample.cafekiosk_review.spring.domain.order.OrderStatus;
import sample.cafekiosk_review.spring.domain.orderproduct.OrderProduct;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {
    private Long id;
    private int totalPrice;
    private LocalDateTime registeredDateTime;
    private List<ProductResponse> products;

    @Builder
    private OrderResponse(final Long id, final int totalPrice, final LocalDateTime registeredDateTime, final List<ProductResponse> products) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.products = products;
    }


    public static OrderResponse of(final Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .registeredDateTime(order.getRegisteredDateTime())
                .products(order.getOrderProducts()
                        .stream()
                        .map(orderProduct -> ProductResponse.of(orderProduct.getProduct())).collect(Collectors.toList()))
                .build();
    }
}
