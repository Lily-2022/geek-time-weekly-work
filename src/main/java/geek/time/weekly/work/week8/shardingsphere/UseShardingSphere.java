package geek.time.weekly.work.week8.shardingsphere;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import geek.time.weekly.work.utils.CURDHelper;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class UseShardingSphere {

    private static final String jdbcUrl = "jdbc:mysql://localhost:3307/sharding_db";
    private static final String username = "root";

    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword("root");
        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setConnectionTimeout(30 * 1000);
        hikariConfig.setConnectionTestQuery("select 1");

        DataSource dataSource = new HikariDataSource(hikariConfig);

        try(Connection connection = dataSource.getConnection()) {

            CURDHelper.addOrUpdateOrDelete(connection, "insert into t_order values(7, 'OK'),(9, 'FAIL')");

            CURDHelper.addOrUpdateOrDelete(connection, "delete from t_order where user_id=1 and order_id = 653758837895442432");
            List<String> list = CURDHelper.query(connection, "select * from t_order;");
            log.info("query result: {}", list.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
