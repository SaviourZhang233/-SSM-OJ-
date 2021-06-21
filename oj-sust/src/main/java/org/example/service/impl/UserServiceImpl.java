package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.service.UserService;
import org.example.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    // 从前端传来的信息的name, password和email都不能为null
    // 注册用户
    // 返回值为0表示注册失败(可能是数据库的连接出现了问题)
    // 返回值为1表示注册成功
    // 返回值为2表示用户名重复
    @Override
    public int registerUser(User user) {
        User user1 = userDao.selectUserByName(user.getName());
        if(user1 != null) {
            // 用户名重复
            return 2;
        }
        // 用户名没有重复
        String password = user.getPassword();
        // 将密码加密
        password = MD5Util.getMD5(password);
        user.setPassword(password);
        int ret = userDao.insertUser(user);
        if (ret == 0) {
            return 0;
        }else {
            return 1;
        }
    }

    // 用户登录
    @Override
    public boolean loginUser(String name, String password) {
        password = MD5Util.getMD5(password);
        User user = userDao.selectUserByNameAndPassword(name, password);
        if(user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    // 注意password和newPassword不能为null, 否则空指针异常, 这里在前端判断
    // 返回值为0代表密码不正确, 返回值为1代表修改成功, 返回值为2代表两次密码相同修改失败
    public int changePassword(String name, String password, String newPassword) {
        String md5Password = MD5Util.getMD5(password);
        // 根据用户名和加密后的密码查找用户
        User user = userDao.selectUserByNameAndPassword(name, md5Password);
        if (user == null) {
            // 如果没有找到就说明密码错误, 修改失败
            return 0;
        } else if (password.equals(newPassword)) {
            // 如果原来密码和现在密码一样, 则也修改失败
            return 2;
        }
        // 这里说明, 根据用户提供的密码正确, 且新密码和旧密码不想相同
        // 将新密码加密后存入数据库
        newPassword = MD5Util.getMD5(newPassword);
        return userDao.updateUserPassword(name, newPassword);
    }

    @Override
    public User selectByName(String username) {
        return userDao.selectUserByName(username);
    }
}
