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
        String key = TIME_LINE_NAMESPACE + userId;
        try (Jedis jedis = RedisPool.getResource()) {
            jedis.zadd(key, (double) System.currentTimeMillis(), blogId + "");
        }
    }

    public List<Integer> get(Set<Integer> userIds) {
        Set<Tuple> allBlog = new HashSet<>();
        try (Jedis jedis = RedisPool.getResource()) {
            for (int userId : userIds) {
                String key = TIME_LINE_NAMESPACE + userId;
                allBlog.addAll(jedis.zrangeByScoreWithScores(key, 0, System.currentTimeMillis()));
            }
        }
        List<Tuple> allBlogList = new ArrayList<>(allBlog);
        Collections.sort(allBlogList, new Comparator<Tuple>() {
            @Override
            public int compare(Tuple o1, Tuple o2) {
                return (int) (o2.getScore() - o1.getScore());
            }
        });
        List<Integer> ret = new ArrayList<>();
        for (Tuple t : allBlogList) {
            if (t == null) continue;
            ret.add(Integer.valueOf(t.getElement()));
        }
        return ret;
    }
}
