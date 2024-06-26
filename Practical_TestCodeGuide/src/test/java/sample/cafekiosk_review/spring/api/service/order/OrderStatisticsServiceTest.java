package sample.cafekiosk_review.spring.api.service.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sample.cafekiosk_review.spring.IntegrationTestSupport;
import sample.cafekiosk_review.spring.client.mail.MailSendClient;
import sample.cafekiosk_review.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk_review.spring.domain.history.mail.MailSendHistoryRepository;
import sample.cafekiosk_review.spring.domain.order.Order;
import sample.cafekiosk_review.spring.domain.order.OrderRepository;
import sample.cafekiosk_review.spring.domain.order.OrderStatus;
import sample.cafekiosk_review.spring.domain.orderproduct.OrderProduct;
import sample.cafekiosk_review.spring.domain.orderproduct.OrderProductRepository;
import sample.cafekiosk_review.spring.domain.product.Product;
import sample.cafekiosk_review.spring.domain.product.ProductRepository;
import sample.cafekiosk_review.spring.domain.product.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static sample.cafekiosk_review.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk_review.spring.domain.product.ProductType.*;

class OrderStatisticsServiceTest extends IntegrationTestSupport {
    @Autowired
    private OrderStatisticsService orderStatisticsService;

    @Autowired
    private OrderRepository orderRepository;
    @DisplayName("결제 완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
    @Test
    void sendOrderStatisticsMail() {
        //given
        LocalDateTime now = LocalDateTime.of(2024, 6, 25, 0, 0);

        Product product1 = createProduct(HANDMADE, "001", 1000);
        Product product2 = createProduct(HANDMADE, "002", 2000);
        Product product3 = createProduct(HANDMADE, "003", 3000);
        List<Product> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);


        Order order1 = createPaymentCompletedOrder(products, LocalDateTime.of(2024, 6, 24, 11, 59, 59));
        Order order2 = createPaymentCompletedOrder(products, now);
        Order order3 = createPaymentCompletedOrder(products, LocalDateTime.of(2024, 6, 25, 11, 59, 59));
        Order order4 = createPaymentCompletedOrder(products, LocalDateTime.of(2024, 6, 26, 0, 0));

        // Mock객체의 행위를 정의했다.
        // stubbing
        when(mailSendClient.sendEmail(any(String.class), any(String.class),
                any(String.class), any(String.class))).thenReturn(true);


        //when

        boolean result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2024, 6, 25), "test@test.com");

        //then
        assertThat(result).isTrue();
        List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
        assertThat(histories).hasSize(1)
                .extracting("content")
                .contains("총 매출 합계는 12000원입니다.");


    }
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private MailSendHistoryRepository mailSendHistoryRepository;




    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        mailSendHistoryRepository.deleteAllInBatch();
    }

    private Order createPaymentCompletedOrder(final List<Product> products, final LocalDateTime now) {
        Order order = Order.builder()
                .products(products)
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .registeredDateTime(now)
                .build();
        return orderRepository.save(order);
    }


    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .type(type)
                .productNumber(productNumber)
                .price(price)
                .sellingStatus(SELLING)
                .name("메뉴 이름")
                .build();
    }
}
