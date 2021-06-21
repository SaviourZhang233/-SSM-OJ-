package org.example.service;

import org.example.domain.Record;

import java.util.List;

public interface RecordService {

    // 新添加一条答题记录
    boolean addRecord(Record record);

    // 查找某个用户的答题记录
    List<Record> selectRecordByName(String name);

    // 查找某个用户某个题目的答题记录
    List<Record> selectRecordByNameAndId(String name, Integer id);
}
