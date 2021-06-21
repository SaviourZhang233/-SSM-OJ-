package org.example.controller;

import org.example.domain.Record;
import org.example.service.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RegisterController {

    @Resource
    private RecordService service;

    // 查询当前页面主人的答题记录
    @RequestMapping("/queryRegister.do")
    @ResponseBody
    public List<Record> queryRegister(HttpSession session) {

        String username = (String) session.getAttribute("username");
        return service.selectRecordByName(username);
    }
}
