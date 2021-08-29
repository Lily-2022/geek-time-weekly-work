package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierImpl {

    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierImpl.class);

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        SimpleSum task = new SimpleSum(1, cyclicBarrier);
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
        int results = task.getResults();
        logger.info("异步计算结果: {}", results);
        logger.info("主线程退出, 使用时间： {} ms", (System.currentTimeMillis() - start));

    }

    static class SimpleSum implements Runnable {
        private int id;
        private CyclicBarrier barrier;
        private int results;

        public SimpleSum(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            synchronized (this) {
                logger.info("id: {}, thread name: {}", id, Thread.currentThread().getName());
                setResults(MathHelper.getSum());
                try {
                    barrier.await();
                    logger.info("线程组任务 {} 结束，其他任务继续", id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
