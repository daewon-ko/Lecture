package jpa.shop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpa.shop.domain.Address;
import jpa.shop.domain.Member;
import jpa.shop.domain.Order;
import jpa.shop.domain.OrderStatus;
import jpa.shop.domain.item.Book;
import jpa.shop.domain.item.Item;
import jpa.shop.exception.NotEnoughStockException;
import jpa.shop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("상품을 주문한다.")
    public void createOrder() {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(getOrder.getStatus(), OrderStatus.ORDER);
        assertEquals(1, getOrder.getOrderitems().size());
        assertEquals(8, item.getStockQuantity());

    }

    @Test
    @DisplayName("재고수량을 초과하는 주문한다.")
    public void orderMoreThanStock() {
        //given
        Member member = createMember();
        Item item = createBook("시골JPA", 10000, 10);

        int orderCount = 11; // 재고보다 많은 수량

        //when


        //then
        Assertions.assertThrows(NotEnoughStockException.class, () ->
                orderService.order(member.getId(), item.getId(), orderCount));
    }

    @Test
    @DisplayName("주문을 취소한다.")
    public void cancelOrder() {
        //given
        Member member = createMember();
        Item item = createBook("시골JPA", 10000, 10);
        int orderCount =2 ;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertEquals(getOrder.getStatus(), OrderStatus.CANCLE);
        Assertions.assertEquals(item.getStockQuantity(), 10);
    }
    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(final String name, final int price, final int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

}
