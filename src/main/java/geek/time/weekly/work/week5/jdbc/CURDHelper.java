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
            for (int i = 0; i < result.size(); i ++) {
                logger.info("index {}: {}", i+1, result.get(i));
            }
        }
    }

    public static int addOrUpdateOrDelete(Connection connection, String sql) {
        logger.info("Going to execute sql: {}", sql);

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            int result = ps.executeUpdate();
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
}
