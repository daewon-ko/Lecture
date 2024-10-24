package thread.start.test;

import util.MyLogger;

public class StartTest4Main {
    public static void main(String[] args) {

        Thread threadA = new Thread(new MultiRunnable("A", 1000), "Thread-A");
        Thread threadB= new Thread(new MultiRunnable("B", 500), "Thread-B");

        threadA.start();
        threadB.start();


    }

    static class MultiRunnable implements Runnable {
        private String name;
        private int time;

        public MultiRunnable(String name, int time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public void run() {
            while (true) {
                MyLogger.log(this.name);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
