package org.example.util;

public class CodeUtil {

    public static String mergeCode(String requestCode, String testCode) {
        // 先从 requestCode 中找到末尾的 } , 并且截取出前面的代码
        int pos = requestCode.lastIndexOf("}");
        if (pos == -1) {
            return null;
        }
        // 把 testCode 拼接到后面, 并再拼接上一个 }
        return requestCode.substring(0, pos) + testCode + "}";
    }

}
