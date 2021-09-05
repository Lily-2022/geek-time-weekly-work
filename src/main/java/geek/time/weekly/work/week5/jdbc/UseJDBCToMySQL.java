package geek.time.weekly.work.week5.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static geek.time.weekly.work.week5.jdbc.CURDHelper.*;

public class UseJDBCToMySQL {

    private  static final Logger logger = LoggerFactory.getLogger(UseJDBCToMySQL.class);

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/geek_time", "root", "Mysql_2021")){
            //add
            int a = addOrUpdateOrDelete(connection, "insert into student(name, remark) values('Timmy', 'body');");
            logger.info("insert result: {}", a);
            //query
            queryDB(connection);
            //update
            int u = addOrUpdateOrDelete(connection, "update student set remark='boy' where name='Timmy';");
            logger.info("update result: {}", u);
            //delete
            int d = addOrUpdateOrDelete(connection, "delete from student where name='Timmy';");
            logger.info("delete result: {}", d);
            queryDB(connection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
