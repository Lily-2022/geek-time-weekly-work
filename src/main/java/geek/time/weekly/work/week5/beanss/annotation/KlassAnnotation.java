package geek.time.weekly.work.week5.beanss.annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class KlassAnnotation {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        System.out.println("init method in annotation ...");
        setId(2);
        setName("test");

    }

    public String toString() {
        return "Klass info: {\"id\": " + id + ", \"name\": \"" + name + "\"}";
    }
}
