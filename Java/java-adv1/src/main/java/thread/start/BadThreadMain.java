package thread.start;

public class BadThreadMain {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + "start()호출 전");
        helloThread.run(); //run() 직접실행
        System.out.println(Thread.currentThread().getName() + "start()호출 후");

        System.out.println(thread.getName() + ": main() end");
    }
}
