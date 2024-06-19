package sample.cafekiosk_review.spring.domain.orderproduct;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk_review.spring.domain.order.Order;
import sample.cafekiosk_review.spring.domain.product.BaseEntity;
import sample.cafekiosk_review.spring.domain.product.Product;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 필요할때 가지고 올 수 있게끔 지연로딩으로 설정
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;


    public OrderProduct(final Order order, final Product product) {
        this.order = order;
        this.product = product;
    }
}
