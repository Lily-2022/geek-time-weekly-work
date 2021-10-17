package week11.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

public class RedisLockDemo {

        private enum EnumSingleton {

            INSTANCE;
            private RedisLockDemo instance;

            EnumSingleton(){
                instance = new RedisLockDemo();
            }
            public RedisLockDemo getSingleton(){
                return instance;
            }
        }

        public static RedisLockDemo getInstance(){
            return EnumSingleton.INSTANCE.getSingleton();
        }

        private JedisPool jedisPool = new JedisPool();


        public boolean lock(String lockValue, int seconds) {
            try(Jedis jedis = jedisPool.getResource()) {
                return "OK".equals(jedis.set(lockValue, lockValue, "NX", "EX", seconds));
            }
        }


        public boolean release(String lock) {
            String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " + "return redis.call('del',KEYS[1]) else return 0 end";
            try(Jedis jedis = jedisPool.getResource()) {
                return jedis.eval(luaScript, Collections.singletonList(lock), Collections.singletonList(lock)).equals(1L);
            }
        }



}
