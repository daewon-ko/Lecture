package collection.list.test.ex2;

public class Item {
    private String name;
    private int price;
    private int quantity;

    public Item(String name, int price, int quantity) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalPrice() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "상품명:"+getName()+", 합계: "+getTotalPrice();
    }
}
