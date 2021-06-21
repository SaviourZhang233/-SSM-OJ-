package org.example.service.impl;

import org.example.dao.ProblemDao;
import org.example.domain.Problem;
import org.example.service.ProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemDao problemDao;

    // 查询所有题目
    @Override
    public List<Problem> selectProblems() {
        return problemDao.selectProblems();
    }

    // 根据q_id查询题目业务
    @Override
    public Problem selectProblemById(Integer q_id) {
        return problemDao.selectProblemById(q_id);
    }

    // 根据标题模糊查询
    @Override
    public List<Problem> selectProblemByTitle(String title) {
        return problemDao.selectProblemByTitle(title);
    }

    // 添加题目
    @Override
    public boolean insertProblem(Problem problem) {
        Integer ret = problemDao.insertProblem(problem);
        if (ret == 1) {
            return true;
        }else {
            return false;
        }
    }
}
