package week11.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception{
        SpringApplication.run(App.class, args);

        Thread thread1 = new Thread(App::lockTest);
        Thread thread2 = new Thread(App::lockTest);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Thread thread3 = new Thread(App::lockTest);
        thread3.start();
        thread3.join();
    }

    private final static String LOCK = "redis_lock";

    private final static int EXPIRE = 3;

    private static int amount = 10;

    public static void lockTest() {
        log.info("lock test:: start sleep 10");

        if (!RedisLockDemo.getInstance().lock(LOCK, EXPIRE)) {
            log.info("lock failed");
            return;
        }

        try {
            Thread.sleep(10000);
            amount -= 1;
            log.info("deduct amount: {}", amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RedisLockDemo.getInstance().release(LOCK);
        log.info("lock test:: end");
    }

}
