package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //     동적쿼리 조회

    /**
     *
     * QUERYDSL 필요
     *
     */
    public List<Order> findAll(OrderSearch orderSearch) {

        String jpql = "select o from Order o join o.member m where o.orderStatus = :status" +
                " and m.name like :name ";
        return em.createQuery(jpql, Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)    // 최대 1000건 조회
                .getResultList();

    }


}
