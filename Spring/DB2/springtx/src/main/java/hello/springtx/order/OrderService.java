package hello.springtx.order;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;


    @Transactional
    public void order(Order order) throws NotEnoughException {
        log.info("order 호출");
        orderRepository.save(order);

        log.info("결제 프로세스 진입");
        if (order.getUsername().equals("예외")) {
            log.info("시스템 예외발생");
            throw new RuntimeException("시스템 예외");
        } else if (order.getUsername().equals("잔고부족")) {
            log.info("잔고부족 비즈니스 예외 발생");
            order.setPayStatus("대기");
            throw new NotEnoughException("잔고가 부족합니다.");
        }else {
            // 정상승인
            log.info("정상 승인");
            order.setPayStatus("완료");

        }

        log.info("결제 프로세스 완료");
    }
}
