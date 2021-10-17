package week11.pubsub;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

@Slf4j
public class Subscriber {

    public Subscriber(final JedisPool jedisPool, final String channelName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("start to subscribe ...");
                try (Jedis jedis = jedisPool.getResource()) {
                    jedis.subscribe(init(), channelName);
                }
            }
        }, "subscribeThread").start();
    }

    private JedisPubSub init() {
        return new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                if (message.isEmpty()) {
                    log.info("subpub end...");
                    System.exit(0);
                }
                log.info("Received message from channel: {}, info is: {}", channel, message);
            }
        };
    }

}
