package com.cyc.dao;

import com.cyc.common.RedisPool;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

@Repository

public class UserTimeLineDao {

    private final static String TIME_LINE_NAMESPACE = "time_lien:";

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

    public List<Integer> get(Set<Integer> userIds) {

        PriorityQueue<TupleWithKey> heap = new PriorityQueue<>((o1, o2) -> {
            double s1 = o1.t.getScore(), s2 = o2.t.getScore();
            if (s1 < s2) return 1;
            else if (s1 > s2) return -1;
            return 0;
        });

        Map<String, Integer> indexForKey = new HashMap<>();
        for (int userId : userIds) {
            String key = buildKey(userId);
            indexForKey.put(key, 0);
        }

        final int MAX_NUM = 20;

        List<Integer> res = new ArrayList<>();

        try (Jedis jedis = RedisPool.getResource()) {
            for (int userId : userIds) {
                String key = buildKey(userId);
                fetch(heap, indexForKey, key, jedis);
            }

            while (!heap.isEmpty() && heap.size() + res.size() < MAX_NUM) {
                TupleWithKey top = heap.poll();
                res.add(Integer.valueOf(top.t.getElement()));
                String key = top.key;
                fetch(heap, indexForKey, key, jedis);
            }

            while (!heap.isEmpty() && res.size() < MAX_NUM) {
                res.add(Integer.valueOf(heap.poll().t.getElement()));
            }
        }
        return res;
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

    private class TupleWithKey {

        Tuple t;
        String key;

        TupleWithKey(Tuple t, String key) {
            this.t = t;
            this.key = key;
        }
    }

    private void fetch(PriorityQueue<TupleWithKey> heap, Map<String, Integer> indexForKey, String key, Jedis jedis) {
        int index = indexForKey.get(key);
        Set<Tuple> sets = jedis.zrangeWithScores(key, index, index);
        for (Tuple t : sets) {
            heap.add(new TupleWithKey(t, key));
        }
        indexForKey.put(key, index + 1);
    }
}
