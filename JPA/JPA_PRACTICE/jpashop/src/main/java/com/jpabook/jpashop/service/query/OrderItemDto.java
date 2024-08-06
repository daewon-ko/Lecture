package com.jpabook.jpashop.service.query;

import com.jpabook.jpashop.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {
    private String name;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        this.name = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}