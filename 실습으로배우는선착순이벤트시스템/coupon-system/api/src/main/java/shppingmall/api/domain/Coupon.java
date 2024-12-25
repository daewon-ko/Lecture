package shppingmall.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.apache.juli.logging.Log;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;


    public Coupon() {
    }

    public Coupon(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }
}
