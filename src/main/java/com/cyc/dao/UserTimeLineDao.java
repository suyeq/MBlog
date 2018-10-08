package com.cyc.dao;

import com.cyc.common.RedisPool;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

@Repository

public class UserTimeLineDao {

    private final static String TIME_LINE_NAMESPACE = "time_lien:";
    private final static String TIME_LINE_0_NAMESPACE = "time_lien_0:";

    public void add(Integer userId, Integer blogId) {
        String key = buildKey(userId);
        try (Jedis jedis = RedisPool.getResource()) {
            jedis.zadd(key, (double) System.currentTimeMillis(), blogId + "");
            long size = jedis.zcard(key);
            final long MAX_NUM = 100;
            if (size > MAX_NUM) {
                jedis.zremrangeByRank(key, -1, -1);
            }
        }
    }

    public List<Integer> get(Integer userId, Set<Integer> focusUserIds) {

        try (Jedis jedis = RedisPool.getResource()) {
            String key0 = buildKey1(userId);
            double lastTime = 0;
            if (jedis.zcard(key0) != 0) {
                Set<Tuple> sets = jedis.zrangeWithScores(key0, -1, -1);
                for (Tuple t : sets) {
                    lastTime = t.getScore();
                }
            }

            for (int fuserId : focusUserIds) {
                String key = buildKey(fuserId);
                Set<Tuple> sets = jedis.zrangeByScoreWithScores(key, lastTime, Double.MAX_VALUE);
                for (Tuple t : sets) {
                    jedis.zadd(key0, t.getScore(), t.getElement());
                }
            }

            final int MAX_NUM = 20;
            long size = jedis.zcard(key0);
            jedis.zremrangeByRank(key0, 0, size - MAX_NUM);

            List<Tuple> list = new ArrayList<>(jedis.zrangeWithScores(key0, 0, -1));
            list.sort((o1, o2) -> {
                if (o1.getScore() > o2.getScore()) return -1;
                else if (o1.getScore() < o2.getScore()) return 1;
                return 0;
            });
            List<Integer> res = new ArrayList<>();
            for (Tuple t : list) {
                res.add(Integer.valueOf(t.getElement()));
            }
            return res;
        }
    }

    public void delete(Integer userId, Integer blogId) {
        String key = buildKey(userId);
        try (Jedis jedis = RedisPool.getResource()) {
            jedis.zrem(key, blogId + "");
        }
    }

    private String buildKey(Integer userId) {
        return TIME_LINE_NAMESPACE + userId;
    }

    private String buildKey1(Integer userId) {
        return TIME_LINE_0_NAMESPACE + userId;
    }
}
