package hello.proxy.app.v1;

public class OrderServiceImpl implements OrderServiceV1 {
    private final OrderRepositoryV1 orderRepositoryV1;

    public OrderServiceImpl(final OrderRepositoryV1 orderRepositoryV1) {
        this.orderRepositoryV1 = orderRepositoryV1;
    }

    @Override
    public void orderItem(final String itemId) {
        orderRepositoryV1.save(itemId);
    }
}
