package sample.cafekiosk_review.unit;

import lombok.Getter;
import sample.cafekiosk_review.unit.beverage.Beverage;
import sample.cafekiosk_review.unit.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class CafeKiosk {
    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void add(Beverage beverage, int cnt) {
        if (cnt <=0) {
            throw new IllegalArgumentException("음료는 1잔 이상부터 주문 가능합니다.");
        }
        IntStream.range(0, cnt).forEach(i -> {
            beverages.add(beverage);
        });

    }

    public void clear() {
        beverages.clear();
    }
    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Beverage beverage : beverages) {
            totalPrice += beverage.getPrice();
        }
        return totalPrice;
    }

    public Order createOrder() {
        return new Order(beverages, LocalDateTime.now());
    }


}
