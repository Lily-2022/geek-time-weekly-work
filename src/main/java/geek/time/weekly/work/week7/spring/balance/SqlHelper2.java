package geek.time.weekly.work.week7.spring.balance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Slf4j
@Service
public class SqlHelper2 {

    @GenericDS
    public void insertDataToDefault(DataSource datasource) {
        String sql = "insert into test(value) values(?)";
        try (Connection conn = datasource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "insert default success");
            ps.executeUpdate();
            log.info("insert data success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GenericDS(value="geek_time")
    public void insertDataToADB(DataSource dataSource) {
        String sql = "insert into test(value) values(?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "insert to geek time");
            ps.executeUpdate();
            log.info("insert to geek time success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GenericDS(value="geek_time2")
    public void insertDataToBDB(DataSource dataSource) {
        String sql = "insert into test(value) values(?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "insert to geek time2");
            ps.executeUpdate();
            log.info("insert to geek time2 success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
