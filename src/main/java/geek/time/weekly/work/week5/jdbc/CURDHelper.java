package geek.time.weekly.work.week5.jdbc;

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

    private static List<String> query(Connection connection) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();
        try {
            ps = connection.prepareStatement("select name,remark from student;");
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    result.add(rs.getString(1) + " - " + rs.getString(2));
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public static void queryDB(Connection connection) {
        List<String> result = query(connection);
        if (Objects.nonNull(result) && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                logger.info("index {}: {}", i + 1, result.get(i));
            }
        }
    }

    public static int addOrUpdateOrDelete(Connection connection, String sql) {
        logger.info("Going to execute sql: {}", sql);
        long startTime = System.currentTimeMillis();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            int result = ps.executeUpdate();
            long endTime = System.currentTimeMillis();
            logger.info("execute add or update sql cost: {} ms", (endTime - startTime));
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return 0;
    }

    public static int[] usePSBatchInsert100WData(Connection connection) {
        PreparedStatement ps = null;
        String sql = getInsertStatement() + prepareInsertSqlForBatch();
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                long time = System.currentTimeMillis();
                extracted(ps, i, time);
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
        String sql = getInsertStatement() + " values" + prepareInsertSqlForBatch() +", "  + prepareInsertSqlForBatch()
                + "," + prepareInsertSqlForBatch() + "," + prepareInsertSqlForBatch() + "," + prepareInsertSqlForBatch()
              ;
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            //long startTime = System.currentTimeMillis();
            for (int i = 0; i < batchNum; i++) {
                long time = System.currentTimeMillis();
                extracted3(ps, i, time);
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
        String sql = getInsertStatement() + " values " + prepareInsertSqlForBatch();
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            long startTime = System.currentTimeMillis();

            for (int k = 0; k < 100; k++) {
                for (int i = 0; i < 10000; i++) {
                    long time = System.currentTimeMillis();
                    extracted(ps, i, time);
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
//            for (int b = 0; b < 10; b ++ ) {
                for (int o = 0; o < 1000; o++) {
                    String sql = getSql();
                    ps = connection.prepareStatement(sql);
                    connection.setAutoCommit(false);
                    long time = System.currentTimeMillis();
                    extracted2(ps, o, time);
                    ps.addBatch();
//                }
//                logger.info("==ps: {}", ps.toString());
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

    private static String getSql() {
        StringBuilder insertSql = new StringBuilder("insert into order_table(o_order_sn,o_user_id,o_receiver_name,o_address, o_payment, o_order_money, o_delivery_fee,o_delivery_sn,o_create_time,o_pay_time,o_shipping_time,o_order_status,o_order_point,o_delivery_time,o_last_update_time,o_delivery_company) values ");
        for (int a = 0; a < 1000; a++) {
            insertSql.append(prepareInsertSqlForBatch())
                    .append(", ");
        }
        String sql = insertSql.substring(0, insertSql.length() - 2);
        sql = sql + ";";
        return sql;
    }

    private static void extracted2(PreparedStatement ps, int i, long time) throws SQLException {
        for (int j = 0; j < 7000; j = j + 7) {
            ps.setInt(j + 1, (i + 1));
            ps.setInt(j + 2, (i + 1));
            ps.setLong(j + 3, time);
            ps.setLong(j + 4, time);
            ps.setLong(j + 5, time);
            ps.setLong(j + 6, time);
            ps.setLong(j + 7, time);
        }

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

    private static void extracted3(PreparedStatement ps, int i, long time) throws SQLException {
        ps.setInt(1, (i + 1));
        ps.setInt(2, (i + 1));
        ps.setLong(3, time);
        ps.setLong(4, time);
        ps.setLong(5, time);
        ps.setLong(6, time);
        ps.setLong(7, time);
        ps.setInt(8, (i + 1));
        ps.setInt(9, (i + 1));
        ps.setLong(10, time);
        ps.setLong(11, time);
        ps.setLong(12, time);
        ps.setLong(13, time);
        ps.setLong(14, time);
        ps.setInt(15, (i + 1));
        ps.setInt(16, (i + 1));
        ps.setLong(17, time);
        ps.setLong(18, time);
        ps.setLong(19, time);
        ps.setLong(20, time);
        ps.setLong(21, time);
        ps.setInt(22, (i + 1));
        ps.setInt(23, (i + 1));
        ps.setLong(24, time);
        ps.setLong(25, time);
        ps.setLong(26, time);
        ps.setLong(27, time);
        ps.setLong(28, time);
        ps.setInt(29, (i + 1));
        ps.setInt(30, (i + 1));
        ps.setLong(31, time);
        ps.setLong(32, time);
        ps.setLong(33, time);
        ps.setLong(34, time);
        ps.setLong(35, time);
//        ps.setInt(36, (i + 1));
//        ps.setInt(37, (i + 1));
//        ps.setLong(38, time);
//        ps.setLong(39, time);
//        ps.setLong(40, time);
//        ps.setLong(41, time);
//        ps.setLong(42, time);
//        ps.setInt(43, (i + 1));
//        ps.setInt(44, (i + 1));
//        ps.setLong(45, time);
//        ps.setLong(46, time);
//        ps.setLong(47, time);
//        ps.setLong(48, time);
//        ps.setLong(49, time);
//        ps.setInt(50, (i + 1));
//        ps.setInt(51, (i + 1));
//        ps.setLong(52, time);
//        ps.setLong(53, time);
//        ps.setLong(54, time);
//        ps.setLong(55, time);
//        ps.setLong(56, time);
//        ps.setInt(57, (i + 1));
//        ps.setInt(58, (i + 1));
//        ps.setLong(59, time);
//        ps.setLong(60, time);
//        ps.setLong(61, time);
//        ps.setLong(62, time);
//        ps.setLong(63, time);
//        ps.setInt(64, (i + 1));
//        ps.setInt(65, (i + 1));
//        ps.setLong(66, time);
//        ps.setLong(67, time);
//        ps.setLong(68, time);
//        ps.setLong(69, time);
//        ps.setLong(70, time);
//        ps.setInt(71, (i + 1));
//        ps.setInt(72, (i + 1));
//        ps.setLong(73, time);
//        ps.setLong(74, time);
//        ps.setLong(75, time);
//        ps.setLong(76, time);
//        ps.setLong(77, time);
//        ps.setInt(78, (i + 1));
//        ps.setInt(79, (i + 1));
//        ps.setLong(80, time);
//        ps.setLong(81, time);
//        ps.setLong(82, time);
//        ps.setLong(83, time);
//        ps.setLong(84, time);

        ps.addBatch();
    }

    public static int[] usePSBatch(Connection connection) {
        PreparedStatement ps = null;
        String sql = "insert into student(name, remark) values(?,?);";
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);


            ps.setString(1, "Hello1");
            ps.setString(2, "Test1");
            ps.addBatch();

            ps.setString(1, "Hello2");
            ps.setString(2, "Test2");
            ps.addBatch();

            int[] count = ps.executeBatch();
            connection.commit();
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

    public static String prepareInsertSql(int index, Long createTime) {

        String insertSql = "insert into order_table(o_order_sn,o_user_id,o_receiver_name,o_address, o_payment, o_order_money, o_delivery_fee,o_delivery_sn,o_create_time,o_pay_time,o_shipping_time,o_order_status,o_order_point,o_delivery_time,o_last_update_time,o_delivery_company) " +
                "values(" + index + "," + index + ",'receiver name','address',1,1.2,1.1,'delivery sn'," + createTime + "," + createTime + "," + createTime + ",1,1," + createTime + "," + createTime + ",'delivery company');";

        return insertSql;
    }

    public static String getInsertStatement() {
        return new StringBuilder("insert into order_table(o_order_sn,o_user_id,o_receiver_name,o_address, o_payment, o_order_money, o_delivery_fee,o_delivery_sn,o_create_time,o_pay_time,o_shipping_time,o_order_status,o_order_point,o_delivery_time,o_last_update_time,o_delivery_company) ").toString();
    }

    public static String prepareInsertSqlForBatch() {
        String values = "(?,?,'receiver name','address',1,1.2,1.1,'delivery sn',?,?,?,1,1,?,?,'delivery company')";
        return values;
    }
}
