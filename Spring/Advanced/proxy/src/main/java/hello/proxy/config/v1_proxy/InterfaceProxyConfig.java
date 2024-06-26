package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(final LogTrace logTrace) {
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(orderServiceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(final LogTrace logTrace) {
        OrderRepositoryImpl orderRepositoryImpl = new OrderRepositoryImpl();
        return new OrderRepositoryInterfaceProxy(orderRepositoryImpl, logTrace);
    }


}
