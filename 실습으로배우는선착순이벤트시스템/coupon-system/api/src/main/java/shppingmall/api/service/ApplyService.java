package shppingmall.api.service;

import org.springframework.stereotype.Service;
import shppingmall.api.producer.CouponProducer;
import shppingmall.api.repository.CouponCountRepository;
import shppingmall.api.repository.CouponRepository;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void apply(Long userId) {
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        // Topic에 userId를 전달
        couponCreateProducer.create(userId);

    }
}
