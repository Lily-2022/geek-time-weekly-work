package geek.time.weekly.work.week5.beanss;

import geek.time.weekly.work.week5.beanss.annotation.KlassAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLoadBeanApproaches {

    private static final Logger logger = LoggerFactory.getLogger(SpringLoadBeanApproaches.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("student");
        logger.info("print out student: {}", student.toString());

        KlassAnnotation klass = (KlassAnnotation) applicationContext.getBean("klassAnnotation");
        logger.info("print out klass info: {}", klass.toString());

    }
}
