package geek.time.weekly.work.week4;

import geek.time.weekly.work.utils.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureImpl {

    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureImpl.class);

    public static void main(String[] args) {
        int result = CompletableFuture.supplyAsync(() -> {
            return MathHelper.getSum();
        }).join();

        logger.info("异步计算结果: {}", result);
    }

}
