package sample.cafekiosk_review.spring.domain.api.service.controller.order;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk_review.spring.domain.api.service.order.OrderService;
import sample.cafekiosk_review.spring.domain.api.service.order.request.OrderCreateRequest;
import sample.cafekiosk_review.spring.domain.api.service.order.response.OrderResponse;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request) {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        return orderService.createOrder(request, registeredDateTime);
    }

}
