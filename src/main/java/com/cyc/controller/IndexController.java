package com.cyc.controller;

import com.cyc.pojo.BlogDetail;
import com.cyc.pojo.User;
import com.cyc.service.imp.BlogServiceImp;
import com.cyc.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogServiceImp blogServiceImp;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = {"/", "/index.html"})
    public ModelAndView showIndexPage() {
        User user = SessionUtil.getUserSession(request);
        if (user == null) {
            return new ModelAndView("redirect:/login.html");
        }
        ModelAndView modelAndView = new ModelAndView("index");
        List<BlogDetail> allBlog = blogServiceImp.getAllBlogOfHome(user.getUserid());
        modelAndView.addObject("allBlog", allBlog);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
