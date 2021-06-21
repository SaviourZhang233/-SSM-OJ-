package org.example.service;

import org.example.domain.Problem;

import java.util.List;

public interface ProblemService {

    // 查询所有题目
    List<Problem> selectProblems();

    // 根据q_id查询题目业务
    Problem selectProblemById(Integer q_id);

    // 根据标题模糊查询
    List<Problem> selectProblemByTitle(String title);

    // 添加题目
    boolean insertProblem(Problem problem);
}
