package com.cyc.util;

import com.cyc.pojo.User;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    private final static String USER_SESSION = "showUserPage";

    public static User getUserSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_SESSION);
    }

    public static void setUserSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute(USER_SESSION, user);
    }

    public static void removeUserSession(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_SESSION);
    }
}
