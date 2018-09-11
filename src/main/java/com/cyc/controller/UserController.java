package com.cyc.controller;

import com.cyc.pojo.User;
import com.cyc.service.UserRelationService;
import com.cyc.service.imp.BlogServiceImp;
import com.cyc.service.imp.UserServiceImp;
import com.cyc.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private BlogServiceImp blogServiceImp;

    @Autowired
    private UserRelationService userRelationService;


    @RequestMapping(value = "/login.html")
    public ModelAndView showLoginPage(Integer page) {
        if (page == null) {
            page = 0;
        }
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("page", page);
        return modelAndView;
    }


    @RequestMapping(value = "/logout.html")
    public String logout() {
        SessionUtil.removeUserSession(request);
        return "redirect:/login.html";
    }


    @RequestMapping(value = "/getUserHeadPic.html")
    @ResponseBody
    public String getUserHeadPic(String userName) {
        User user = userServiceImp.getUserByUserName(userName);
        return user.getHeadpic() + "";
    }


    @RequestMapping(value = "/checkLogin.html")
    public ModelAndView checkLogin(String userName, String password) {
        boolean isValidUser = userServiceImp.checkPassword(userName, password);
        if (!isValidUser) {
            return new ModelAndView("redirect:/login.html", "error", "用户名或密码错误。");
        }
        User user = userServiceImp.getUserByUserName(userName);
        SessionUtil.setUserSession(request, user);
        return new ModelAndView("redirect:/index.html");
    }


    @RequestMapping(value = "/checkRegister.html")
    public ModelAndView checkRegister(String userName, String password, String confirmPassword, Integer headpic) {
        System.out.println(userName);
        System.out.println(headpic);
        if (!password.equals(confirmPassword)) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login.html");
            modelAndView.addObject("error", "两次输入的密码不一致");
            modelAndView.addObject("page", 1);
            return modelAndView;
        }
        boolean isValidUser = userServiceImp.checkUserNameIllegal(userName);
        if (!isValidUser) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login.html");
            modelAndView.addObject("error", "用户名已经存在");
            modelAndView.addObject("page", 1);
            return modelAndView;
        }
        userServiceImp.addUser(userName, confirmPassword, headpic);
        User user = userServiceImp.getUserByUserName(userName);
        SessionUtil.setUserSession(request, user);
        return new ModelAndView("redirect:/index.html");
    }


    @RequestMapping(value = "/user.html")
    public ModelAndView showUserPage(Integer userId, Integer page) {
        ModelAndView modelAndView = new ModelAndView("user");
        User user = SessionUtil.getUserSession(request);
        modelAndView.addObject("user", user);
        User theUser;
        if (userId == null) {
            theUser = user;
        } else {
            theUser = userServiceImp.getUserByUserId(userId);
        }
        modelAndView.addObject("theUser", theUser);
        if (page == null) {
            page = 0;
        }
        if (page == 0) {
            modelAndView.addObject("userBlog", blogServiceImp.getAllBlogByUserId(theUser.getUserid()));
        } else if (page == 1) {
            modelAndView.addObject("follows", userRelationService.getFollowers(theUser.getUserid()));
        } else if (page == 2) {
            modelAndView.addObject("follows", userRelationService.getFollowings(theUser.getUserid()));
        }
        modelAndView.addObject("page", page);
        return modelAndView;
    }


    @RequestMapping(value = "/follow.html")
    public String follow(Integer userId) {
        User user = SessionUtil.getUserSession(request);
        userRelationService.follow(user.getUserid(), userId);
        return "redirect:/user.html?userId=" + userId;
    }
}
