package com.cyc.dao;

import com.cyc.common.RedisPool;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRelationDao {

    private final static String FANS_NAMESPACE = "fans:";
    private final static String FOCUS_NAMESPACE = "focus:";

    public void addFans(Integer userId, Integer fansId) {
        String key = FANS_NAMESPACE + userId;
        try (Jedis jedis = RedisPool.getResource()) {
            jedis.sadd(key, fansId + "");
            RedisPool.returnResource(jedis);
        }
    }

    public void addFocus(Integer userId, Integer focusId) {
        String key = FOCUS_NAMESPACE + userId;
        try (Jedis jedis = RedisPool.getResource()) {
            jedis.sadd(key, focusId + "");
            RedisPool.returnResource(jedis);
        }
    }

    public Set<Integer> getFollowers(Integer userId) {
        Set<Integer> ret;
        String key = FANS_NAMESPACE + userId;
        try (Jedis jedis = RedisPool.getResource()) {
            Set<String> set = jedis.smembers(key);
            ret = convertStringSet2IntegerSet(set);
            RedisPool.returnResource(jedis);
        }
        return ret;
    }

    public Set<Integer> getFollowings(Integer userId) {
        Set<Integer> ret;
        String key = FOCUS_NAMESPACE + userId;
        try (Jedis jedis = RedisPool.getResource()) {
            Set<String> set = jedis.smembers(key);
            ret = convertStringSet2IntegerSet(set);
            RedisPool.returnResource(jedis);
        }
        return ret;
    }

    private Set<Integer> convertStringSet2IntegerSet(Set<String> set) {
        Set<Integer> ret = new HashSet<>();
        for (String s : set) {
            ret.add(Integer.valueOf(s));
        }
        return ret;
    }
}
