package geek.time.weekly.work.week7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CURDHelper {

    private static final Logger logger = LoggerFactory.getLogger(CURDHelper.class);

    public static int[] usePSBatchInsert100WData(Connection connection) {
        PreparedStatement ps = null;
        String sql = "insert into test(value) values (?)";
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                ps.setString(1, String.valueOf(System.currentTimeMillis()));
                ps.addBatch();
            }
            int[] count = ps.executeBatch();
            connection.commit();
            long endTime = System.currentTimeMillis();
            logger.info("time consuming for batch count size: {}, : {} ms", count.length, (endTime - startTime));
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public static int[] usePSBatchInsertByBatchWithMultiThread(Connection connection, int batchNum) {
        PreparedStatement ps = null;
        String sql = "insert into test(value) values (?), (?), (?), (?), (?), (?), (?), (?), (?), (?)";
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            //long startTime = System.currentTimeMillis();
            for (int i = 0; i < batchNum; i++) {
                extracted3(ps, System.currentTimeMillis());
            }
            int[] count = ps.executeBatch();
            connection.commit();
            //long endTime = System.currentTimeMillis();
            //logger.info("time consuming for batch count size: {}, : {} ms", count.length, (endTime - startTime));
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public static void usePSBatchInsert100WDataByBatch(Connection connection) {
        PreparedStatement ps = null;
        String sql = "insert into test(value) values (?)";
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            long startTime = System.currentTimeMillis();

            for (int k = 0; k < 100; k++) {
                for (int i = 0; i < 10000; i++) {
                    ps.setString(1, String.valueOf(System.currentTimeMillis()));
                    ps.addBatch();
                }
                int[] count = ps.executeBatch();
                connection.commit();
            }
            long endTime = System.currentTimeMillis();
            logger.info("time consuming for batch : {} ms", (endTime - startTime));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void usePSBatchInsertWithMultiValues(Connection connection) {
        PreparedStatement ps = null;
        long startTime = System.currentTimeMillis();
        try {
//            for (int b = 0; b < 200; b ++ ) {
                for (int o = 0; o < 1000; o++) {
                    String sql = getSql();
                    ps = connection.prepareStatement(sql);
                    connection.setAutoCommit(false);
                    extracted2(ps, System.currentTimeMillis());
                    ps.addBatch();
                    int[] count = ps.executeBatch();
                    connection.commit();
                }

//            }
            long endTime = System.currentTimeMillis();
            logger.info("time consuming for batch : {} ms", (endTime - startTime));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private static String getSql() {
        StringBuilder insertSql = new StringBuilder("insert into test(value) values");
        for (int a = 0; a < 1000; a++) {
            insertSql.append("(?), ");
        }
        String sql = insertSql.substring(0, insertSql.length() - 2);
        sql = sql + ";";
        return sql;
    }

    private static void extracted2(PreparedStatement ps, long time) throws SQLException {
        for (int j = 0; j < 1000; j ++) {
            ps.setString(j + 1, String.valueOf(time));

        }

    }

    private static void extracted3(PreparedStatement ps, long time) throws SQLException {
        ps.setString(1, String.valueOf(time));
        ps.setString(2, String.valueOf(time));
        ps.setString(3, String.valueOf(time));
        ps.setString(4, String.valueOf(time));
        ps.setString(5, String.valueOf(time));
        ps.setString(6, String.valueOf(time));
        ps.setString(7, String.valueOf(time));
        ps.setString(8, String.valueOf(time));
        ps.setString(9, String.valueOf(time));
        ps.setString(10, String.valueOf(time));
        ps.addBatch();

    }

    private static void extracted(PreparedStatement ps, int i, long time) throws SQLException {
        ps.setInt(1, (i + 1));
        ps.setInt(2, (i + 1));
        ps.setLong(3, time);
        ps.setLong(4, time);
        ps.setLong(5, time);
        ps.setLong(6, time);
        ps.setLong(7, time);
        ps.addBatch();
    }

}
