package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncImpl {
    private static final Logger logger = LoggerFactory.getLogger(SyncImpl.class);

    public static void main(String[] args) throws Exception{
        int[] results = new int[1];
        Worker worker = new Worker(results);
        worker.start();
        Thread.sleep(100);

        worker.lock.lock();
        worker.lock.unlock();
        logger.info("异步执行结果: {}", results[0]);

    }

    static class Worker extends Thread {

        ReentrantLock lock = new ReentrantLock();
        int[] results;

        public Worker(int[] results) {
            this.results = results;
        }

        @Override
        public void run() {

            lock.lock();
            try {
                results[0] = MathHelper.getSum();
            } finally {
                lock.unlock();
            }
        }
    }

}
