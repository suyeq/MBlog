package com.cyc.service.imp;

import com.cyc.BaseTest;
import com.cyc.pojo.BlogDetail;
import com.cyc.service.BlogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
public class BlogServiceImpTest extends BaseTest {
    @Autowired
    BlogService blogServiceImp;

    @Test
    public void getAllBlogOfHome() {
        List<BlogDetail> allBlogs = blogServiceImp.getAllBlogOfHome(1);
        System.out.println(allBlogs);
    }
}