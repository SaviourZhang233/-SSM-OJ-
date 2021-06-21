package org.example.dao;

import org.example.domain.Problem;

import java.util.List;

public interface ProblemDao {

    // 查询所有题目
    List<Problem> selectProblems();

    // 根据题目id查找
    Problem selectProblemById(Integer q_id);

    // 根据题目标题查找
    List<Problem> selectProblemByTitle(String title);

    // 添加题目
    int insertProblem(Problem problem);
}
