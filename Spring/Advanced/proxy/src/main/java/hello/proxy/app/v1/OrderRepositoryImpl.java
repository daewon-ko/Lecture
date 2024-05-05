package hello.proxy.app.v1;

public class OrderRepositoryImpl implements OrderRepositoryV1{
    @Override
    public void save(final String itemId) {
        //저장로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생");
        }
        sleep(1000);

    }

    private void sleep(final int milliTimes) {
        try {
            Thread.sleep(milliTimes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
