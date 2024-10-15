package collection.list.test.ex2;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void displayItems() {
        System.out.println("장바구니 상품 출력");
        int totalPrice = getTotalPrice();
        System.out.println("전체 가격 합 :" + totalPrice);

    }

    private int getTotalPrice() {
        int totalPrice= 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
            System.out.println(item.toString());
        }
        return totalPrice;
    }
}
