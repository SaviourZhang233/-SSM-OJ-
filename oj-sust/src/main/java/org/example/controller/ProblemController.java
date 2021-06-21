package org.example.controller;

import org.example.domain.*;
import org.example.service.ProblemService;
import org.example.service.RecordService;
import org.example.service.Task;
import org.example.util.CodeUtil;
import org.example.util.NumberUtil;
import org.example.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProblemController {

    @Resource
    private ProblemService service;

    @Resource
    private RecordService recordService;

    // 获取所有题目
    @RequestMapping("/queryProblem.do")
    @ResponseBody
    public List<Problem> queryProblem () {
        List<Problem> problems = service.selectProblems();
        return problems;
    }

    // 获取一个题目
    @RequestMapping("/queryOneProblem.do")
    @ResponseBody
    public Problem queryProblemOne (HttpServletRequest request) {

        String q_id = request.getParameter("q_id");
        if (q_id == null || "".equals(q_id)) {
            return null;
        }else {
            Problem problem = service.selectProblemById(Integer.parseInt(q_id));
            return problem;
        }
    }

    // 根据id或title模糊查找题目
    @RequestMapping("/search.do")
    @ResponseBody
    public List<Problem> searchProblemOne(String keyWord) {

        if (keyWord == null || "".equals(keyWord)) {
            return service.selectProblems();
        }
        List<Problem> problems = new ArrayList<>();
        boolean isNum = NumberUtil.isNum(keyWord);
        if (isNum) {
            // 可以转换为数字
            // 将其转换为数字, 在根据id查找题目
            Integer q_id = Integer.parseInt(keyWord);
            Problem byId = service.selectProblemById(q_id);
            // 将这个题目插入到结果顺序表中
            problems.add(byId);
        }
        // 根据关键字查找
        List<Problem> byKeyWord = service.selectProblemByTitle(keyWord);
        // 将查找到的有题目加入顺序表
        problems.addAll(byKeyWord);
        return problems;
    }

    // 判题并提交记录
    @RequestMapping(value="/compile.do", produces={"application/json;charset=UTF-8;" })
    @ResponseBody
    public CompileResponse judgeProblem (@RequestBody CompileRequest compileRequest,
                                         HttpServletRequest request) throws IOException {
        if (compileRequest == null) {
            System.out.println("空空");
            return null;
        }
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        // 根据id在数据库中查找题目
        Problem problem = service.selectProblemById(compileRequest.getId());
        // 获取测试代码
        String testCode = problem.getTestCode();
        // 获取用户提交的代码
        String requestCode = compileRequest.getCode();
        // 将用户代码和数据库中的测试代码拼接
        String finalCode = CodeUtil.mergeCode(requestCode, testCode);
//        System.out.println(finalCode);
        // 创建 Task 对象, 借助 Task 对象来编译运行这个代码
        Task task = new Task();
        Question question = new Question();
        question.setCode(finalCode);
        Answer answer = null;
        String taskRet = null;
        String time = null;
        try{
            answer = task.compileAndRun(question);
            time = TimeUtil.getTime();
            // 0没问题1编译出错2出现异常
            int errNum = answer.getErrno();
            if (errNum == 0) {
                // 没问题
                taskRet = "运行通过";
            } else if(errNum == 1) {
                // 编译出错
                taskRet = "编译错误";
            } else {
                // 运行出错
                taskRet = "运行出错";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 将答题记录存入数据库
        Record record = new Record();
        record.setTime(time);
        record.setU_name(username);
        record.setQ_id(compileRequest.getId());
        record.setResult(taskRet);
        recordService.addRecord(record);
        // 将运行结果封装为结果对象
        CompileResponse compileResponse = new CompileResponse();
        compileResponse.setReason(answer.getReason());
        compileResponse.setStdout(answer.getStdout());
        compileResponse.setTaskRet(taskRet);
        return compileResponse;
    }

    // 添加题目
    @RequestMapping(value = "/addProblem.do", method = RequestMethod.POST)
    public String addProblem(Problem problem) {

        String title = problem.getTitle();

        String description = problem.getDescription();

        String templateCode = problem.getTemplateCode();

        String testCode = problem.getTestCode();

        String owner = problem.getOwner();
        if ("".equals(title) == true || "".equals(description) == true
            || "".equals(templateCode) == true || "".equals(testCode) == true
            || "".equals(owner) == true) {
            // 输入有空
            return "redirect:page/addProblemErr1.html";
        }
        boolean ret = service.insertProblem(problem);
        if (ret) {
            // 添加成功
            return "redirect:page/addProblemSucc.html";
        } else {
            // 添加失败, 可能是网络问题
            return "redirect:page/addProblemErr2.html";
        }
    }
}
