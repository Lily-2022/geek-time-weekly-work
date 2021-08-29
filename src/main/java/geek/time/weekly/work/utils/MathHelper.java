package geek.time.weekly.work.utils;

import geek.time.weekly.work.week4.CountDownLatchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathHelper {
    private static final Logger logger = LoggerFactory.getLogger(MathHelper.class);

    public static int getSum() {
        logger.info("going to return fibo result with int");
       return fibo(10);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    public static Integer getIntegerSum() {
        logger.info("going to return fibo result with integer");
        return fibo(10);
    }
}
