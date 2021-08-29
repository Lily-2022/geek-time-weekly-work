package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

public class SemaphoreImpl {

    private static final Logger logger = LoggerFactory.getLogger(SemaphoreImpl.class);

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(0);
        Worker worker = new Worker(1, semaphore);
        worker.start();
        semaphore.acquire();
        logger.info("异步结果为： {}", worker.getResults());
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public int getResults() {
            return results;
        }

        public void setResults(int results) {
            this.results = results;
        }

        private int results;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            logger.info("worker: {} is working.", this.num);
            setResults(MathHelper.getSum());
            logger.info("worker: {} is free.", this.num);
            semaphore.release();
        }
    }
}
