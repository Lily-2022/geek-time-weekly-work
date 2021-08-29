package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchImpl {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchImpl.class);

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        simpleSum task = new simpleSum(1, countDownLatch);
        new Thread(task).start();
        countDownLatch.await();
        int results = task.getResults();
        logger.info("异步计算结果: {}", results);
        logger.info("主线程退出, 使用时间： {} ms", (System.currentTimeMillis() - start));

    }

    static class simpleSum implements Runnable {
        private int id;
        private CountDownLatch latch;
        private int results;

        public simpleSum(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                logger.info("id: {}, thread name: {}", id, Thread.currentThread().getName());
                setResults(MathHelper.getSum());
                logger.info("线程组任务 {} 结束，其他任务继续", id);
                latch.countDown();
            }
        }

        public int getResults() {
            return results;
        }

        public void setResults(int results) {
            this.results = results;
        }
    }

}
