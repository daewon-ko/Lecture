package sample.cafekiosk_review.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk_review.unit.beverage.Americano;
import sample.cafekiosk_review.unit.beverage.Latte;
import sample.cafekiosk_review.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void addServeral() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano, 2);

        Latte latte = new Latte();
        cafeKiosk.add(latte);

        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(2)).isEqualTo(latte);
    }

    @Test
    void addZeroBeverage() {
        CafeKiosk cafeKiosk = new CafeKiosk();

        assertThatThrownBy(() -> cafeKiosk.add(new Americano(), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상부터 주문 가능합니다.");
    }

    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        cafeKiosk.remove(americano);

        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(0);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }


    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages().size())
                .isEqualTo(0);

        assertThat(cafeKiosk.getBeverages())
                .isEmpty();
    }

    @Test
    void createOrderWithCurrentTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder(LocalDateTime.of(2024, 6, 17, 10, 0));

        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
        assertThat(order.getBeverages().size()).isEqualTo(1);
    }

    @Test
    void creaeOrderOutsideTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024, 6, 17, 9, 59)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
    }
}
