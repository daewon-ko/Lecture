package shppingmall.api.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CouponProducer {
    private final KafkaTemplate<String, Long> kafkaTemplate;

    public CouponProducer(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void create(Long userId) {
        kafkaTemplate.send("coupon_create", userId);
    }


}
