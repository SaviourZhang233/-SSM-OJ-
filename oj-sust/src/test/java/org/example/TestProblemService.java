package org.example;

import org.example.domain.Problem;
import org.example.service.ProblemService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestProblemService {

    // 测试查找所有题目信息
    @Test
    public void testSelectProblems() {
        String config = "conf/applicationContext.xml";
        ApplicationContext axt = new ClassPathXmlApplicationContext(config);
        ProblemService service = (ProblemService) axt.getBean("problemServiceImpl");
        List<Problem> problems = service.selectProblems();
        for(Problem problem : problems) {
            System.out.println(problem);
        }
    }

    // 测试根据题目id查找
    @Test
    public void testSelectProblemById() {
        String config = "conf/applicationContext.xml";
        ApplicationContext axt = new ClassPathXmlApplicationContext(config);
        ProblemService service = (ProblemService) axt.getBean("problemServiceImpl");
        Problem problem = service.selectProblemById(1);
        System.out.println(problem);
        problem = service.selectProblemById(3);
        System.out.println(problem);

    }

    // 测试根据标题模糊查
    @Test
    public void testSelectProblemByTitle() {
        String config = "conf/applicationContext.xml";
        ApplicationContext axt = new ClassPathXmlApplicationContext(config);
        ProblemService service = (ProblemService) axt.getBean("problemServiceImpl");
        List<Problem> problems = null;
        problems = service.selectProblemByTitle("加");
        System.out.println("根据 加 模糊查找的信息");
        for (Problem problem : problems) {
            System.out.println(problem);
        }
    }
//    // 题目的标题
//    private String title;
//    // 题目的难度级别
//    private String level;
//    // 题目的详细描述
//    private String description;
//    // 题目的模板代码
//    private String templateCode;
//    // 题目的测试用例代码
//    private String testCode;
//    // 题目的拥有者
//    private String owner;

    // 测试添加
    @Test
    public void testInsertProblem() {
        String config = "conf/applicationContext.xml";
        ApplicationContext axt = new ClassPathXmlApplicationContext(config);
        ProblemService service = (ProblemService) axt.getBean("problemServiceImpl");
        Problem problem1 = new Problem();
        problem1.setTitle("两数相加");
        problem1.setLevel("简单");
        problem1.setDescription("两数相加");
        problem1.setTemplateCode("hello");
        problem1.setTestCode("haha");
        problem1.setOwner("陕西科技大大学");
        boolean ret = service.insertProblem(problem1);
        if (ret) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

        Problem problem2 = new Problem();
        problem2.setTitle("两数相减");
        problem2.setLevel("简单");
        problem2.setDescription("两数相加");
        problem2.setTemplateCode("hello");
        problem2.setTestCode("haha");
        problem2.setOwner("陕西科技大大学");
        ret = service.insertProblem(problem2);
        if (ret) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

        Problem problem3 = new Problem();
        problem3.setTitle("两数相乘");
        problem3.setLevel("简单");
        problem3.setDescription("两数相加");
        problem3.setTemplateCode("hello");
        problem3.setTestCode("haha");
        problem3.setOwner("陕西科技大大学");
        ret = service.insertProblem(problem3);
        if (ret) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

        Problem problem4 = new Problem();
        problem4.setTitle("两数相除");
        problem4.setLevel("简单");
        problem4.setDescription("两数相加");
        problem4.setTemplateCode("hello");
        problem4.setTestCode("haha");
        problem4.setOwner("陕西科技大大学");
        ret = service.insertProblem(problem4);
        if (ret) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

        Problem problem5 = new Problem();
        problem5.setTitle("三数相除");
        problem5.setLevel("简单");
        problem5.setDescription("两数相加");
        problem5.setTemplateCode("hello");
        problem5.setTestCode("haha");
        problem5.setOwner("陕西科技大大学");
        ret = service.insertProblem(problem5);
        if (ret) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

    }
}
