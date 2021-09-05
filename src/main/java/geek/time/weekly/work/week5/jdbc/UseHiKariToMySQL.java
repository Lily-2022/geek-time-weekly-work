package geek.time.weekly.work.week5.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;

import static geek.time.weekly.work.week5.jdbc.CURDHelper.addOrUpdateOrDelete;
import static geek.time.weekly.work.week5.jdbc.CURDHelper.queryDB;

public class UseHiKariToMySQL {

    private static final Logger logger = LoggerFactory.getLogger(UseHiKariToMySQL.class);

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/geek_time";
    private static final String username = "root";

    public static void main(String[] args) {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword("Mysql_2021");
        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setConnectionTimeout(30 * 1000);
        hikariConfig.setConnectionTestQuery("select 1");

        DataSource dataSource = new HikariDataSource(hikariConfig);

        try (Connection connection = dataSource.getConnection()) {
            //add
            int a = addOrUpdateOrDelete(connection, "insert into student(name, remark) values('Cody', 'body');");
            logger.info("Add result: {}", a);
            //query
            queryDB(connection);

            //update
            int u = addOrUpdateOrDelete(connection, "update student set remark='boy' where name='Cody';");
            logger.info("update result: {}", u);
            //delete
            int d = addOrUpdateOrDelete(connection, "delete from student where name='Cody';");
            logger.info("delete result: {}", d);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ((HikariDataSource) dataSource).close();
        }
    }

}
