package org.example;

import org.example.domain.User;
import org.example.service.ProblemService;
import org.example.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserService {

    public static ApplicationContext axt = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
//    // 用户姓名
//    private String name;
//    // 用户密码
//    private String password;
//    // 用户性别
//    private String gender;
//    // 用户年龄
//    private Integer age;
//    // 用户邮箱
//    private String email;
//    // 用户学校
//    private String school;

    // 测试注册用户,
    @Test
    public void testRegisterUser() {
        UserService service = (UserService) axt.getBean("userServiceImpl");
        User user0 = new User();
        user0.setName("张三");
        user0.setPassword("12345");
        user0.setGender("男");
        user0.setAge(20);
        user0.setEmail("123@123.com");
        user0.setSchool("陕西科技大学");
        int ret = service.registerUser(user0);
        if (ret == 1) {
            System.out.println("user0(用户所有信息都完整)注册测试通过");
        } else {
            System.out.println("user0(用户所有信息都完整)注册测试未通过");
        }
        User user1 = new User();
        user1.setName("李四");
        user1.setPassword("999999");
        user1.setGender("男");
        user1.setEmail("123@123.com");
        user1.setSchool("陕西科技大学");
        ret = service.registerUser(user1);
        if (ret == 1) {
            System.out.println("user1(用户name,password,email字段完整,其余字段不完整)注册测试通过");
        } else {
            System.out.println("user1(用户name,password,email字段完整,其余字段不完整)注册测试未通过");
        }
        User user2 = new User();
        user2.setName("张三");
        user2.setPassword("888999");
        user2.setGender("男");
        user2.setAge(21);
        user2.setEmail("123@123.com");
        user2.setSchool("陕西科技大学");
        ret = service.registerUser(user2);
        if (ret == 2) {
            System.out.println("user2(表示用户名和数据库中有重名)注册测试通过");
        } else {
            System.out.println("user2(表示用户名和数据库中有重名)注册测未试通过");
        }
    }

    // 测试登录模块
    @Test
    public void testLoginUser() {
        UserService service = (UserService) axt.getBean("userServiceImpl");
        String name1 = "张三";
        String password1 = "12345";
        boolean ret = service.loginUser(name1, password1);
        if (ret) {
            System.out.println("用户名和密码匹配测试通过");
        } else {
            System.out.println("用户名和密码匹配测试未通过");
        }
        String name2 = "李四";
        String password2 = "123345";
        ret = service.loginUser(name2, password2);
        if (ret) {
            System.out.println("用户名和密码不匹配测试未通过");
        } else {
            System.out.println("用户名和密码不匹配测试通过");
        }
    }

    // 测试修改密码模块
    // 注意password和newPassword不能为null, 否则空指针异常, 这里在前端判断
    // 返回值为0代表密码不正确, 返回值为1代表修改成功, 返回值为2代表两次密码相同修改失败
    // public int changePassword(String name, String password, String newPassword)
    @Test
    public void testChangePassword() {
        UserService service = (UserService) axt.getBean("userServiceImpl");
        String name1 = "张三";
        String password1 = "12345";
        String newPassword1 = "1234567";
        int ret = service.changePassword(name1, password1, newPassword1);
        if (ret == 1) {
            System.out.println("原密码正确且新密码和原密码不相同测试通过");
        } else {
            System.out.println("原密码正确且新密码和原密码不相同测试未通过");
        }
        String name2 = "张三";
        String password2 = "1234444";
        String newPassword2 = "99999";
        ret = service.changePassword(name2, password2, newPassword2);
        if (ret == 0) {
            System.out.println("原密码错误修改不成功测试通过");
        } else {
            System.out.println("原密码错误修改不成功测试未通过");
        }
        String name3 = "李四";
        String password3 = "999999";
        String newPassword3 = "999999";
        ret = service.changePassword(name3, password3, newPassword3);
        if (ret == 2) {
            System.out.println("原密码和新密码相同修改失败测试通过");
        } else {
            System.out.println("原密码和新密码相同修改失败测试未通过");
        }

    }

}
