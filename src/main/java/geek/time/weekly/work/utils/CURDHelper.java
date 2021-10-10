package geek.time.weekly.work.utils;

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

    public static List<String> query(Connection connection, String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
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

    public static void queryDB(Connection connection, String sql) {
        List<String> result = query(connection, sql);
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
}
