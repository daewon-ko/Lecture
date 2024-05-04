package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV2Test {

    @Test
    void begin_end_level2() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("Hello");
        TraceStatus status2 = trace.beginSync(status.getTraceId(), "Hello2 From TwoVersion");
        trace.end(status);
        trace.end(status2);

    }
}
