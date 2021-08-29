package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockImpl {
    private static final Logger logger = LoggerFactory.getLogger(LockImpl.class);

    public static void main(String[] args) throws Exception{
        int[] results = new int[1];
        Object lock = new Object();

        Worker worker = new Worker(lock, results);
        worker.start();
        Thread.sleep(100);

        synchronized (lock) {
        }
        logger.info("异步执行结果: {}", results[0]);

    }

    static class Worker extends Thread {

        private Object o;
        int[] results;

        public Worker(Object o, int[] results) {
            this.o = o;
            this.results = results;
        }

        @Override
        public void run() {

            synchronized (o) {
                results[0] = MathHelper.getSum();
            }
        }
    }

}
