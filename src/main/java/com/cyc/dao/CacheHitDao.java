package com.cyc.dao;

import com.cyc.common.RedisPool;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

/**
 * 读写缓存命中率数据
 **/
@Repository
public class CacheHitDao {

    private static final String CACHE_HIT_KEY = "cache-hit";
    private static final String CACHE_MISS_KEY = "cache-miss";

    public void hit() {
        try (Jedis jedis = RedisPool.getResource()) {
            jedis.incr(CACHE_HIT_KEY);
        }
    }

    public void miss() {
        try (Jedis jedis = RedisPool.getResource()) {
            jedis.incr(CACHE_MISS_KEY);
        }
    }
}
