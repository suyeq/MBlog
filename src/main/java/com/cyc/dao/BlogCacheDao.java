package com.cyc.dao;

import com.cyc.common.RedisPool;
import com.cyc.pojo.Blog;
import com.cyc.util.SerializeUtil;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class BlogCacheDao {

    private final static String BLOG_NAMESPACE = "blog:";

    public void addBlog(Blog blog) {
        String key = BLOG_NAMESPACE + blog.getBlogid();
        try (Jedis jedis = RedisPool.getResource()) {
            jedis.set(key, SerializeUtil.writeBlogObject(blog));
        }
    }

    public Blog getBlog(int blogId) {
        Blog blog = null;
        String key = BLOG_NAMESPACE + blogId;
        try (Jedis jedis = RedisPool.getResource()) {
            String s = jedis.get(key);
            if (s != null) {
                blog = SerializeUtil.readBlogObject(s);
            }
        }
        return blog;
    }
}
