package sample.cafekiosk_review.unit;

import lombok.Getter;
import sample.cafekiosk_review.unit.beverage.Beverage;
import sample.cafekiosk_review.unit.order.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class CafeKiosk {
    private final List<Beverage> beverages = new ArrayList<>();
    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);


    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void add(Beverage beverage, int cnt) {
        if (cnt <= 0) {
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

    public Order createOrder(LocalDateTime currentDateTime) {

//        LocalDateTime currentDateTime
//                = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }


        return new Order(beverages, currentDateTime);
    }


}
