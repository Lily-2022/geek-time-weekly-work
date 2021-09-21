package geek.time.weekly.work.week7.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DataSourceApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-week7.xml");

        SQLHelper sqlHelper = (SQLHelper) applicationContext.getBean("sQLHelper");
        sqlHelper.insertDataToDefault();


    }

}
