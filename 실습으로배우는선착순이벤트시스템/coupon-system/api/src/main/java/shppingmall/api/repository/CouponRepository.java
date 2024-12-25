package shppingmall.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shppingmall.api.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
