package collection.map.test.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Product, Long> products = new HashMap<>();

    public void add(Product product, Long quantity) {
        Long existingQuantity = products.getOrDefault(product, 0L);
        products.put(product, existingQuantity + quantity);
    }


    public void printAll() {
        for (Map.Entry<Product, Long> entry : products.entrySet()) {
            System.out.println("Product : " + entry.getKey() + "Quantity : " + entry.getValue());
        }
    }

    public void minus(Product product, Long quantity) {
        Long cnt = products.getOrDefault(product, 0L);

        if (cnt <= quantity) {
            products.remove(product);
        } else {
            products.put(product, cnt - quantity);
        }

    }


}
