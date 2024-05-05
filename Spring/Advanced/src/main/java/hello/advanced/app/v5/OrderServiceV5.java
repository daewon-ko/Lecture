package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepositoryV5;
    private final TraceTemplate template;

    @Autowired
    public OrderServiceV5(final OrderRepositoryV5 orderRepositoryV4, final LogTrace logTrace) {

        this.orderRepositoryV5 = orderRepositoryV4;
        this.template = new TraceTemplate(logTrace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()",()->{
            orderRepositoryV5.save(itemId);
            return null;
        });

    }
}
