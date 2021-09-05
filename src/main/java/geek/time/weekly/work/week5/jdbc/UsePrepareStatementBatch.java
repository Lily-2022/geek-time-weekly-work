package geek.time.weekly.work.week5.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

import static geek.time.weekly.work.week5.jdbc.CURDHelper.queryDB;
import static geek.time.weekly.work.week5.jdbc.CURDHelper.usePSBatch;

public class UsePrepareStatementBatch {

    private  static final Logger logger = LoggerFactory.getLogger(UsePrepareStatementBatch.class);


    public static void main(String[] args) throws ClassNotFoundException{

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/geek_time", "root", "Mysql_2021")) {
            //add
            int[] a = usePSBatch(connection);
            logger.info("batch insert result: {}", a.length);

            queryDB(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
