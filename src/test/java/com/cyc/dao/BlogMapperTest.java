package com.cyc.dao;

import com.cyc.BaseTest;
import com.cyc.pojo.Blog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
public class BlogMapperTest extends BaseTest
{
    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void selectByUserId()
    {
        List<Blog> blog = blogMapper.selectByUserId(1);
        System.out.println(blog);
        Assert.assertTrue(blog.size() != 0);
    }

    @Test
    public void selectAllBlogId()
    {
        List<Blog> blog = blogMapper.selectAll();
        System.out.println(blog);
        Assert.assertTrue(!blog.isEmpty());
    }
}