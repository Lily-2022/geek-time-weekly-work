package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinImpl {
    private static final Logger logger = LoggerFactory.getLogger(JoinImpl.class);

    public static void main(String[] args) throws Exception{
        int[] results = new int[1];

        Worker worker = new Worker(results);
        worker.start();
        worker.join();

        logger.info("异步执行结果: {}", results[0]);

    }

    static class Worker extends Thread {

        private int[] results;

        public Worker(int[] results) {
            this.results = results;
        }

        @Override
        public void run() {
            results[0] = MathHelper.getSum();

        }
    }

}
