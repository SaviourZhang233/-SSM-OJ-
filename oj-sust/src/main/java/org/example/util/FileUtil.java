package org.example.util;

import java.io.*;

// 操作文件工具类
public class FileUtil{

    // 从指定的文件中一次把所有的内容读出来
    public static String readFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(fis,"GBK"); //最后的"GBK"根据文件属性而定，如果不行，改成"UTF-8"试试
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return stringBuilder.toString();
        }
    }

    // 把 content 中的内容写入到 filePath 对应的文件中
    public static void writeFile(String filePath, String content) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            byte[] bytes = content.getBytes();
            out.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
