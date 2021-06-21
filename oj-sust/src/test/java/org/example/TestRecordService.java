package org.example;

import org.example.domain.Record;
import org.example.service.RecordService;
import org.example.service.UserService;
import org.example.util.TimeUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestRecordService {

    public static ApplicationContext axt = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
//    // 新添加一条答题记录
//    boolean addRecord(Record record);
//
//    // 查找某个用户的答题记录
//    List<Record> selectRecordByName(String name);
//
//    // 查找某个用户某个题目的答题记录
//    List<Record> selectRecordByNameAndId(String name, Integer id);

    // 测试新添加一条答题记录
//    @Test
//    public void TestAddRecord() {
//        RecordService service = (RecordService) axt.getBean("recordServiceImpl");
//        Record record = new Record();
//        record.setU_name("张三");
//        record.setQ_id(1);
//        record.setResult(2);
//        record.setTime(TimeUtil.getTime());
//        boolean ret = service.addRecord(record);
//        if (ret) {
//            System.out.println("添加答题记录测试通过");
//        } else {
//            System.out.println("添加答题记录测试未通过");
//        }
//
//        Record record1 = new Record();
//        record1.setU_name("张三");
//        record1.setQ_id(2);
//        record1.setResult(1);
//        record1.setTime(TimeUtil.getTime());
//        ret = service.addRecord(record1);
//        if (ret) {
//            System.out.println("添加答题记录测试通过");
//        } else {
//            System.out.println("添加答题记录测试未通过");
//        }
//
//        Record record2 = new Record();
//        record2.setU_name("李四");
//        record2.setQ_id(2);
//        record2.setResult(1);
//        record2.setTime(TimeUtil.getTime());
//        ret = service.addRecord(record2);
//        if (ret) {
//            System.out.println("添加答题记录测试通过");
//        } else {
//            System.out.println("添加答题记录测试未通过");
//        }
//    }

    // 测试查找某个用户的答题记录
    @Test
    public void TestSelectRecordByName() {
        RecordService service = (RecordService) axt.getBean("recordServiceImpl");
        String name = "张三";
        List<Record> records = service.selectRecordByName(name);
        for (Record record : records) {
            System.out.println(record);
        }
    }

    // 测试查找某个用户某个题目的答题记录
    @Test
    public void TestSelectRecordByNameAndId() {
        RecordService service = (RecordService) axt.getBean("recordServiceImpl");
        String name = "张三";
        int id = 1;
        List<Record> records = service.selectRecordByNameAndId(name, id);
        for (Record record : records) {
            System.out.println(record);
        }
    }
}
