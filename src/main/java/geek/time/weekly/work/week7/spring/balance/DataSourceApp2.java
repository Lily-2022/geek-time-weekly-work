package geek.time.weekly.work.week7.spring.balance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;


public class DataSourceApp2 {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-week7-balance.xml");

        SqlHelper2 sqlHelper = (SqlHelper2) applicationContext.getBean("sqlHelper2");
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        sqlHelper.insertDataToDefault((DataSource) dynamicDataSource.determineCurrentLookupKey());
//        sqlHelper.insertDataToADB((DataSource) dynamicDataSource.determineCurrentLookupKey());
//        sqlHelper.insertDataToBDB((DataSource) dynamicDataSource.determineCurrentLookupKey());


    }

}
