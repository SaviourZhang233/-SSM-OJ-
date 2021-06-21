package org.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

    public static String getTime() {
        Date nowTime = new Date();
        return sdf.format(nowTime);
    }

    public static void main(String[] args) {
        System.out.println(getTime());
    }
}
