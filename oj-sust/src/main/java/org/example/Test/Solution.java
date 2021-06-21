package org.example.Test;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

public class Solution {
    // 计算1 + 1
    public static int func() {
        return 2;
    }

    public static void main(String[] args) throws Exception {
        int ret = func();
        if (ret == 2) {
            System.out.println("恭喜你通过所有测试用例");
        } else {
            throw new Exception("抱歉,未能通过所有测试用例");
        }
    }
}

