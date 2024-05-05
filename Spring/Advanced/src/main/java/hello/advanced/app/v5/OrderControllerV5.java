package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {
    private final OrderServiceV5 orderServiceV5;
    private final TraceTemplate template;

    @Autowired
    public OrderControllerV5(final OrderServiceV5 orderServiceV5, final LogTrace logTrace) {
        this.orderServiceV5 = orderServiceV5;
        this.template = new TraceTemplate(logTrace);

    }

    @GetMapping("/v5/request")
    public String request(String itemID) {
        return template.execute("OrderController.request()", () -> {
            orderServiceV5.orderItem(itemID);
            return "OK";
        });

    }
}
