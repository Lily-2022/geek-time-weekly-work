package geek.time.weekly.work.week1;

import java.lang.reflect.Method;

public class CustomizedClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Object clazz = new CustomizedClassLoader().loadClass("Hello").newInstance();
            Method method = clazz.getClass().getMethod("hello");
            method.invoke(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] byteValue = FileUtil.getFileByteValue();
        for (int i = 0; i < byteValue.length; i ++) {
            byteValue[i] = (byte) (255 - byteValue[i]);
        }
        return defineClass(name, byteValue, 0, byteValue.length);
    }
}
