package week11.pubsub;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

@Slf4j
public class Publisher {

    public Publisher(JedisPool jedisPool, String channelName) {
      log.info("Start to publish order");
      try (Jedis jedis = jedisPool.getResource()) {
          int num = 0;
          for (int i = 0; i < 10; i ++) {
              num = new Random().nextInt(8);
              try {
                  Thread.sleep(3000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              jedis.publish(channelName, "order num " + num);
          }
          jedis.publish(channelName, "");
      }

    }
}
