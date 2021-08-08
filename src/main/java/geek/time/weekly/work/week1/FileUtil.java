package geek.time.weekly.work.week1;

import java.io.*;

public class FileUtil {

    public static byte[] getFileByteValue() {
        try {
            File file = new File("/Users/liuyedong/lena_workspace/geek-time-weekly-work/src/main/resources/geek.time.weekly.work.week1/Hello.xlass");
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeToFile(byte[] bytes) {
        try {
            FileOutputStream outputStream = new FileOutputStream("Hello.class");
            outputStream.write(bytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
