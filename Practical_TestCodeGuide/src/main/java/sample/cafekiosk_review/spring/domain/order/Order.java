package sample.cafekiosk_review.spring.domain.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk_review.spring.domain.orderproduct.OrderProduct;
import sample.cafekiosk_review.spring.domain.product.BaseEntity;
import sample.cafekiosk_review.spring.domain.product.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;

    private LocalDateTime registeredDateTime;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)      // 주문입장에선 어떤주문들로 이루어졌는지 아는게 좋을 것 같으므로 양방향 관계 서정
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Order(List<Product> products, LocalDateTime registeredDateTime) {
        this.orderStatus = OrderStatus.INIT;
        this.totalPrice = calculateTotalPrice(products);
        this.registeredDateTime = registeredDateTime;
        this.orderProducts = products.stream().map(p -> new OrderProduct(this, p)).collect(Collectors.toList());
    }


    public static Order create(final List<Product> products, LocalDateTime registeredDateTime) {
        return new Order(products, registeredDateTime);
    }

    private static int calculateTotalPrice(final List<Product> products) {
        return products.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }
}


