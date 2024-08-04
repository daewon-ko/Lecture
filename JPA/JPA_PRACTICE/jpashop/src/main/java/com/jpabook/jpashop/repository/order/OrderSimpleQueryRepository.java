package com.jpabook.jpashop.repository.order;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {

        return em.createQuery("select new com.jpabook.jpashop.repository.order.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.orderStatus, d.address) from Order o join o.member m join o.delivery d", OrderSimpleQueryDto.class).getResultList();
    }
}
