package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {
    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderService1(logTrace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(orderController.getClass().getClassLoader(), new Class[]{OrderControllerV1.class},
                new LogTraceBasicHandler(orderController, logTrace));
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderService1(final LogTrace logTrace) {
        OrderServiceV1 orderService = new OrderServiceImpl(orderRepositoryV1(logTrace));
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(orderService.getClass().getClassLoader(), new Class[]{OrderServiceV1.class},
                new LogTraceBasicHandler(orderService, logTrace));
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(final LogTrace logTrace) {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryImpl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(orderRepositoryV1.getClass().getClassLoader(), new Class[]{OrderRepositoryV1.class},
                new LogTraceBasicHandler(orderRepositoryV1, logTrace));
        return proxy;

    }
}
