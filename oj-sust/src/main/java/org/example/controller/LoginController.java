package org.example.controller;

import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 登录
@Controller
public class LoginController {

    @Resource
    private UserService service;

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request,
                              HttpServletResponse response,
                              String username, String password,
                              @RequestParam(required = false) String automatic) {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        boolean ret = service.loginUser(username, password);
        if (ret) {
            // 数据库中查找到信息
            // 判断用户是否勾选自动登录
            if ("yes".equals(automatic)) {
                // 选中自动登录
                // 创建Cookie对象
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                // 设置有效时间为十天
                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);
                // 设置关联路径
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                // 发送Cookie给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            session.setAttribute("username", username);
//            session.setAttribute("isLog", "yes");
//            session.setAttribute("password", password);
            return "redirect:page/function.html";
        } else {
            // 未查找到信息
            // 显示登录失败跳转会登录页面
            session.setAttribute("isLog", "passwordError");
            return "redirect:page/loginErr.html";
        }

    }
}
