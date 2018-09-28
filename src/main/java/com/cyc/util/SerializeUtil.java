package com.cyc.util;

import com.cyc.pojo.Blog;

public class SerializeUtil {

    private static final String separator = "/////";

    public static String writeBlogObject(Blog blog) {
        StringBuilder s = new StringBuilder();
        s.append(blog.getUserid()).append(separator);
        s.append(blog.getBlogid()).append(separator);
        s.append(DateUtil.formatDate(blog.getPublishtime())).append(separator);
        s.append(blog.getContent());
        return s.toString();
    }

    public static Blog readBlogObject(String s) {
        Blog blog = new Blog();
        String[] token = s.split(separator);
        blog.setUserid(Integer.valueOf(token[0]));
        blog.setBlogid(Integer.valueOf(token[1]));
        blog.setPublishtime(DateUtil.parseDate(token[2]));
        if(token.length > 3) {
            blog.setContent(token[3]);
        }
        return blog;
    }
}
