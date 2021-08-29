package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;
import java.util.concurrent.Future;

public class FutureImpl {
    private static final Logger logger = LoggerFactory.getLogger(FutureImpl.class);

    public static void main(String[] args) {
        ExecutorService executors = Executors.newCachedThreadPool();
        Future<Integer> task = executors.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return MathHelper.getIntegerSum();
            }
        });
        executors.shutdown();

        try {
            logger.info("异步结果：{}", task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
