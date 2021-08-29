package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueImpl {

    private static final Logger logger = LoggerFactory.getLogger(SynchronousQueueImpl.class);

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue synchronousQueue = new SynchronousQueue();
        Worker worker = new Worker(synchronousQueue);
        worker.start();

        logger.info("异步计算结果： {}", synchronousQueue.take());

    }

    static class Worker extends Thread {

        private SynchronousQueue synchronousQueue;
        public Worker(SynchronousQueue synchronousQueue) {
            this.synchronousQueue = synchronousQueue;
        }

        @Override
        public void run() {
            try {
                synchronousQueue.put(MathHelper.getSum());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
