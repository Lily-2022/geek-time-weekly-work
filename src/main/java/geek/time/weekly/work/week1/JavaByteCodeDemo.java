package geek.time.weekly.work.week1;

public class JavaByteCodeDemo {
    public static void main(String[] args) {
        sum();
    }

    public static double sum () {
        double num = 1.0f;
        for (int i = 0; i < 100; i ++ ){
            num += i;
            if (num > 50) {
                break;
            }
        }
        return num;
    }
}

