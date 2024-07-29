
package org.example.jpql;

import jakarta.persistence.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private int orderAmount;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Order order;

    @Embedded
    private Address address;
}
