package sample.cafekiosk_review.spring.api.controller.order;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk_review.spring.api.APiResponse;
import sample.cafekiosk_review.spring.api.service.order.OrderService;
import sample.cafekiosk_review.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk_review.spring.api.service.order.response.OrderResponse;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/api/v1/orders/new")
    public APiResponse<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        return APiResponse.ok(orderService.createOrder(request.toServiceRequest(), registeredDateTime));
    }

}
