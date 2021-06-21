package org.example.controller;


import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.*;

@Controller
public class IsLoginController {

    @Resource
    private UserService service;

    // 判断用户是否已经登录
    @RequestMapping("/isLogin.do")
    public String isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 获取cookie信息
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        // 遍历Cookie
        if (cookies != null) {
            // 有cookie
            for (Cookie cookie : cookies) {
                // 获取用户名和密码
                // 由于cookie有两个一个是的key是username, 一个key是password
                // 所以这里需要判断
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                if ("username".equals(cookieName)) {
                    // 说明这个cookie是用户名
                    username = cookieValue;
                }else if ("password".equals(cookieName)) {
                    // 说明这个cookie是密码
                    password = cookieValue;
                }
            }
        } else {
            // cookie为空
            // 跳转到登录页面
            return "redirect:page/index.html";
        }

        // 判断得到的用户名和密码是否为空
        if (username != null && password != null) {
            // 不为空, 代表得到了用户名和密码
            // 根据用户名和密码在数据库中查找
            boolean ret = service.loginUser(username, password);
            if (ret) {
                // 在数据库中查到信息
                // 跳转到答题界面
                // 将用户名存入session
                session.setAttribute("username", username);
//                session.setAttribute("password", password);
                return "redirect:page/function.html";
            } else {
                // 在数据库中未查到信息
                // 跳转到登录界面
                return "redirect:page/index.html";
            }
        } else {
            // 代表cookie中没有用户名或者密码, 不能自动登录
            // 跳转到登录界面
            return "redirect:page/index.html";
        }
    }
}
