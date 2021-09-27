package geek.time.weekly.work.week7.spring.nobalance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DataSourceApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-week7.xml");

        SqlHelper sqlHelper = (SqlHelper) applicationContext.getBean("sqlHelper");
        sqlHelper.insertDataToDefault();
        sqlHelper.insertDataToADB();
        sqlHelper.insertDataToBDB();


    }

}
