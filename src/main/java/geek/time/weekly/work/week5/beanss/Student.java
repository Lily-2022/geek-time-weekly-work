package geek.time.weekly.work.week5.beanss;
import java.io.Serializable;

public class Student implements Serializable {

    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {}

    public void init() {
        System.out.println("hello...........");
    }

    public static Student create() {
        return new Student(102, "KK102");
    }

    @Override
    public String toString() {
        return "Studen: {\"id\": " + id + ", \"name\": \"" + name + "\"}";
    }


}
