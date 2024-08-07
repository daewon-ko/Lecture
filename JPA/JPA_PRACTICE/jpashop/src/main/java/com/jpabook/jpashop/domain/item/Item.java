package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.domain.Category;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@BatchSize(size = 100)
public abstract class Item {
    @Id
    @GeneratedValue()
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스로직

    /**
     *
     * Stock 증가
     */
    public void addStock(int stockQuantity) {
        this.stockQuantity+=stockQuantity;
    }
    /*
    * Stock 감소
     */

    public void removeStock(int stockQuantity) {
        if(this.stockQuantity-stockQuantity < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= stockQuantity;
    }

}
