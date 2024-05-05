package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderServiceV1;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemID) {
        TraceStatus status = null;
        try {

            status = trace.begin("OrderController.request()");
            orderServiceV1.orderItem(status.getTraceId(), itemID);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }


        return "OK";
    }
}
