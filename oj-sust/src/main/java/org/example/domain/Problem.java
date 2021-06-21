package org.example.domain;

// 实体类. 这个类的每个实例就对应数据库中的一条记录
// 这个类包含的字段, 就和数据库表中的字段是相匹配的.
public class Problem {
//    q_id int primary key auto_increment,
//    title varchar(50),
//    level varchar(50),
//    description varchar(4096),
//    templateCode varchar(4096),
//    testCode varchar(4096),
//    organization varchar(100),
//    owner varchar(200)
    // 题目的编号
    private Integer q_id;
    // 题目的标题
    private String title;
    // 题目的难度级别
    private String level;
    // 题目的详细描述
    private String description;
    // 题目的模板代码
    private String templateCode;
    // 题目的测试用例代码
    private String testCode;
    // 题目的拥有者
    private String owner;

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(Integer q_id) {
        this.q_id = q_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "q_id=" + q_id +
                ", title='" + title + '\'' +
                ", level='" + level + '\'' +
                ", description='" + description + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", testCode='" + testCode + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
