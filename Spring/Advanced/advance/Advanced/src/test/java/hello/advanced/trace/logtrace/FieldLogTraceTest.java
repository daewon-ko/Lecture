package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {
    FieldLogTrace trace = new FieldLogTrace();

    @Test
    void begin_end_leve2() {
        TraceStatus status = trace.begin("Hello1");
        TraceStatus status1 = trace.begin("Hello2");
        trace.end(status1);
        trace.end(status);

    }

}
