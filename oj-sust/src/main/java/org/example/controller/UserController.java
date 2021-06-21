package org.example.controller;

import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Resource
    private UserService service;

    // 获取用户信息
    @RequestMapping("/getUser.do")
    @ResponseBody
    public User getUser(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = service.selectByName(username);
        return user;
    }

    // 修改密码
    @RequestMapping("/changePassword.do")
    public String changePassword(HttpServletRequest request) {

        HttpSession session = request.getSession();
        // 根据session中的用户名查找用户
        String username = (String) session.getAttribute("username");
        // 获取用户提交的原密码和新密码
        String oldPassword = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        if ("".equals(oldPassword) || "".equals(newPassword)) {
            // 密码不能为空
            return "redirect:page/changeErr1.html";
        }
        if (newPassword.equals(oldPassword)) {
            // 新旧密码相同, 修改失败
            return "redirect:page/changeErr2.html";
        }
        int ret = service.changePassword(username, oldPassword, newPassword);
        if (ret == 1) {
            // 修改成功
            return "redirect:page/changeSucc.html";
        } else {
            // 密码不正确
            return "redirect:page/changeErr3.html";
        }
    }

    // 切换账号
    @RequestMapping("/switch.do")
    public String switchUser(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean ret = service.loginUser(username, password);
        if (ret) {
            // 切换成功
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            // 将cookie中的信息变换, 防止下一次登录还是上一个用户的信息
            Cookie cookie1 = new Cookie("username","");
            cookie1.setMaxAge(0); //
            cookie1.setPath(request.getContextPath());
            response.addCookie(cookie1);
            Cookie cookie2 = new Cookie("password","");
            cookie2.setMaxAge(0); //
            cookie2.setPath(request.getContextPath());
            response.addCookie(cookie2);
            return "redirect:page/function.html";
        } else {
            // 切换失败
            return "redirect:page/switchErr.html";
        }
    }

    // 退出登录
    @RequestMapping("/signOut.do")
    public String signOut(HttpServletRequest request ,HttpServletResponse response,
                          HttpSession session) {
        // 将session注销, 且将cookie中的用户名和密码信息注销
        if (session != null) {
            session.invalidate();
        }
        Cookie cookie1 = new Cookie("username","");
        cookie1.setMaxAge(0);
        cookie1.setPath(request.getContextPath());
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("password","");
        cookie2.setMaxAge(0);
        cookie2.setPath(request.getContextPath());
        response.addCookie(cookie2);
        return "redirect:page/index.html";
    }

    // 注册新用户
    @RequestMapping("/register.do")
    public String register(User user ,HttpServletRequest request, HttpServletResponse response) {

//        // 用户姓名
//        private String name;
//        // 用户密码
//        private String password;
//        // 用户性别
//        private String gender;
//        // 用户年龄
//        private Integer age;
//        // 用户邮箱
//        private String email;
//        // 用户学校
//        private String school;
        int ret = service.registerUser(user);
        if (ret == 0) {
            // 注册失败(可能是数据库的连接出现了问题)
            return "redirect:page/registerErr1.html";
        } else if (ret == 2) {
            // 注册失败,用户名重复
            return "redirect:page/registerErr2.html";
        } else {
            // 注册成功
            // 将用户名和密码存入session
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getName());
//            session.setAttribute("password", user.getPassword());
            return "redirect:page/function.html";
        }
    }
}
