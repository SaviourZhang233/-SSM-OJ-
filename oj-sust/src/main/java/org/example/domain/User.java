package org.example.domain;

// 用户类
public class User {
//    name varchar(1000) not null,
//    password varchar(1000) not null,
//    gender varchar(10),
//    age int,
//    email varchar(50) not null,
//    school varchar(50)
    // 用户姓名
    private String name;
    // 用户密码
    private String password;
    // 用户性别
    private String gender;
    // 用户年龄
    private Integer age;
    // 用户邮箱
    private String email;
    // 用户学校
    private String school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
