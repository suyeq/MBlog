package com.cyc.service;

import com.cyc.pojo.Blog;
import com.cyc.pojo.BlogDetail;

import java.util.List;

public interface BlogService {

    List<BlogDetail> getAllBlogOfHome(int userId);

    List<BlogDetail> getAllBlogByUserId(int userId);

    Blog getBlogByBlogId(int id);

    void addBlog(int userId, String content);

    void editBlog(int blogId, String content);

    void deleteBlog(int userId,  int blogId);
}
