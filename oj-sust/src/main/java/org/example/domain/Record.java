package org.example.domain;

public class Record {
//    u_name varchar(50) not null,
//    q_id int not null,
//    result int not null,
//    time varchar(100) not null

    private String u_name;
    private Integer q_id;
    private String result;
    private String time;

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public Integer getQ_id() {
        return q_id;
    }

    public void setQ_id(Integer q_id) {
        this.q_id = q_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Record{" +
                "u_name='" + u_name + '\'' +
                ", q_id=" + q_id +
                ", result=" + result +
                ", time='" + time + '\'' +
                '}';
    }
}
