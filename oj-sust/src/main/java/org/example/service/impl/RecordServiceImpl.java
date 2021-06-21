package org.example.service.impl;

import org.example.dao.RecordDao;
import org.example.domain.Record;
import org.example.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordDao recordDao;

    // 新添加一条答题记录
    @Override
    public boolean addRecord(Record record) {
        int ret = recordDao.insertRecord(record);
        if (ret == 1) {
            return true;
        } else {
            return false;
        }
    }

    // 查找某个用户的答题记录
    @Override
    public List<Record> selectRecordByName(String name) {
        return recordDao.selectRecordByName(name);
    }

    // 查找某个用户某个题目的答题记录
    @Override
    public List<Record> selectRecordByNameAndId(String name, Integer id) {
        return recordDao.selectRecordByNameAndId(name, id);
    }
}
