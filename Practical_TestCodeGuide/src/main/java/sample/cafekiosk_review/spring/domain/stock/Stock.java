package sample.cafekiosk_review.spring.domain.stock;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk_review.spring.domain.product.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNumber;

    private int quantity;

    @Builder
    private Stock(final String productNumber, final int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public static Stock create(final String productNumber, final int quantity) {

        return Stock.builder()
                .productNumber(productNumber)
                .quantity(quantity)
                .build();
    }

    public boolean isQuantityLessThan(final int quantity) {
        return this.quantity < quantity;
    }

    /**
     * Service Layer에서 isQuantityLessThan 체크를 해준다고 해도,
     * Stock Domain은 외부에 대해서 전혀 알지 못한다.
     * 즉 단위테스트이고, 수량을 차감한다고 했을 때, 올바른 수량 차감로직이 되도록 보장해야한다.
     * 즉 둘의 상황은 아주 다르다. 그렇기 때문에 발생할 수 있는 상황이 다르므로
     * 메시지도 다르다.
     */
    public void deductQuantity(final int quantity) {
        if (isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException("차감할 재고 수량이 없습니다.");
        }
        this.quantity -= quantity;

    }
}
