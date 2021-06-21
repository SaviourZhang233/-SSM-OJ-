package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.domain.Record;

import java.util.List;

public interface RecordDao {

    // 新增一条答题记录
    int insertRecord(Record record);

    // 根据用户名查找答题记录
    List<Record> selectRecordByName(String u_name);

    // 根据用户名和题目id查找答题记录
    List<Record> selectRecordByNameAndId(@Param("u_name") String u_name, @Param("q_id") Integer q_id);
}
