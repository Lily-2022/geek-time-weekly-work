package geek.time.weekly.work.week7.spring.nobalance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@Service
public class SqlHelper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GenericDS
    public void insertDataToDefault() {
        String sql = "insert into test(value) values(?)";
        jdbcTemplate.update(sql, "insert to default");
    }

    @GenericDS(value="geek_time")
    public void insertDataToADB() {
        String sql = "insert into test(value) values(?)";
        jdbcTemplate.update(sql, "insert geek_time");
    }

    @GenericDS(value="geek_time2")
    public void insertDataToBDB() {
        String sql = "insert into test(value) values(?)";
        jdbcTemplate.update(sql, "insert into geek_time2");
    }

}
