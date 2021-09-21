package geek.time.weekly.work.week7;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.CountDownLatch;

import static geek.time.weekly.work.week7.CURDHelper.*;

@Slf4j
public class InsertDataByBatch {

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/geek_time?rewriteBatchedStatements=true&characterEncoding=UTF-8&useUnicode=true&allowMultiQueries=true";
    private static final String username = "root";

    public static void main(String[] args) {

        DataSource dataSource = createDateSource();
        //commit 100w data one by one  --- 607146 ms
        /*try(Connection connection = dataSource.getConnection()) {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i ++) {
                String str = prepareInsertSql(i+1, System.currentTimeMillis());
                addOrUpdateOrDelete(connection, str);
            }
            long endTime = System.currentTimeMillis();
            log.info("completed insert 100w records into db and cost: {} ms", (endTime - startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try(Connection connection = dataSource.getConnection()) {

            //commit 100w data in one batch  --- 164851 ms
//            usePSBatchInsert100WData(connection);
            //split into 100 batch for 1000000 data  --- 21386 ms

            usePSBatchInsert100WDataByBatch(connection);

//                usePSBatchInsertWithMultiValues(connection);
//            long startTime = System.currentTimeMillis();
//            CountDownLatch latch = new CountDownLatch(4);
//            new Thread(() -> {
//                usePSBatchInsertByBatchWithMultiThread(connection,25000);
//                latch.countDown();
//            }).start();
//            new Thread(() -> {
//                usePSBatchInsertByBatchWithMultiThread(connection,25000);
//                latch.countDown();
//            }).start();
//            new Thread(() -> {
//                usePSBatchInsertByBatchWithMultiThread(connection, 25000);
//                latch.countDown();
//            }).start();
//            new Thread(() -> {
//                usePSBatchInsertByBatchWithMultiThread(connection, 25000);
//                latch.countDown();
//            }).start();
//            latch.await();
//            long endTime = System.currentTimeMillis();
//            log.info("time consuming for batch : {} ms", (endTime - startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static DataSource createDateSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword("Mysql_2021");
        hikariConfig.setMaximumPoolSize(200);
        hikariConfig.setConnectionTimeout(30 * 1000);
        hikariConfig.setConnectionTestQuery("select 1");

        return new HikariDataSource(hikariConfig);
    }



}
