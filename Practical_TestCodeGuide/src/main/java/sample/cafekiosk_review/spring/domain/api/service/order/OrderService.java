package sample.cafekiosk_review.spring.domain.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk_review.spring.domain.api.service.order.request.OrderCreateRequest;
import sample.cafekiosk_review.spring.domain.api.service.order.response.OrderResponse;
import sample.cafekiosk_review.spring.domain.order.Order;
import sample.cafekiosk_review.spring.domain.order.OrderRepository;
import sample.cafekiosk_review.spring.domain.product.Product;
import sample.cafekiosk_review.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;



    public OrderResponse createOrder(final OrderCreateRequest request, LocalDateTime registedDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        // Product 조회
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        // Order 생성

        Order order = Order.create(products, registedDateTime);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);

    }


}
