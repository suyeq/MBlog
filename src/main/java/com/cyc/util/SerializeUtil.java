package com.cyc.util;

import com.cyc.pojo.Blog;

public class SerializeUtil {

    public static String writeBlogObject(Blog blog) {
        StringBuilder s = new StringBuilder();
        s.append(blog.getUserid()).append(",");
        s.append(blog.getBlogid()).append(",");
        s.append(DateUtil.formatDate(blog.getPublishtime())).append(",");
        s.append(blog.getContent());
        return s.toString();
    }

    public static Blog readBlogObject(String s) {
        Blog blog = new Blog();
        String[] token = s.split(",");
        blog.setUserid(Integer.valueOf(token[0]));
        blog.setBlogid(Integer.valueOf(token[1]));
        blog.setPublishtime(DateUtil.parseDate(token[2]));
        blog.setContent(token[3]);
        return blog;
    }

}
