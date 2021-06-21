package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.domain.User;

public interface UserDao {

    // 添加用户信息
    int insertUser(User user);

    // 根据用户名和密码查找用户
    User selectUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    // 根据用户名查找用户
    User selectUserByName(String name);

    // 修改用户密码
    int updateUserPassword(@Param("name") String name, @Param("newPassword") String newPassword);
}
