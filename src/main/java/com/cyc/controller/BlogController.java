package com.cyc.controller;

import com.cyc.pojo.User;
import com.cyc.service.imp.BlogServiceImp;
import com.cyc.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BlogController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    BlogServiceImp blogServiceImp;

    @RequestMapping(value = "/publishBlog.html")
    public ModelAndView publishBlog(String blogContent) {
        User user = SessionUtil.getUserSession(request);
        blogServiceImp.addBlog(user.getUserid(), blogContent);
        return new ModelAndView("redirect:/index.html");
    }

    @RequestMapping(value = "/editBlog.html")
    @ResponseBody
    public String deleteBlog(Integer blogId) {
        blogServiceImp.deleteBlog(blogId);
        return "success";
    }
}
