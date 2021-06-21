package org.example.domain;

// 表示编译结果的响应类
public class CompileResponse {
    // 运行信息
    private String taskRet;
    // 原因
    private String reason;
    // 标准输出
    private String stdout;

    public String getTaskRet() {
        return taskRet;
    }

    public void setTaskRet(String taskRet) {
        this.taskRet = taskRet;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }
}
