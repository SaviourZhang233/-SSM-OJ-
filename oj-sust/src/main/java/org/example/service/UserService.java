package org.example.service;

import org.example.domain.User;

public interface UserService {

    // 注册用户
    int registerUser(User user);

    // 登录
    boolean loginUser(String name, String password);

    // 修改密码
    int changePassword(String name, String password,String newPassword);

    // 根据用户名查找用户信息
    User selectByName(String username);
}
