package week11.pubsub;

import redis.clients.jedis.JedisPool;

public class App {

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool();
        String channel = "order";

        Subscriber subscriber = new Subscriber(jedisPool, channel);
        Publisher publisher = new Publisher(jedisPool, channel);
    }

}
