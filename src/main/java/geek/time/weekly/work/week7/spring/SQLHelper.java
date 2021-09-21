package geek.time.weekly.work.week7.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SQLHelper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GenericDS
    public void insertDataToDefault() {
        String sql = "insert into test(value) values(?)";
        jdbcTemplate.update(sql, "insertIntoDefaultDatasource");
    }

    @GenericDS("geek_time")
    public void insertDataToADB() {
        String sql = "insert into test(value) values(?)";
        jdbcTemplate.update(sql, "insertIntoGeekTime");
    }

    @GenericDS("geek_time2")
    public void insertDataToBDB() {
        String sql = "insert into test(value) values(?)";
        jdbcTemplate.update(sql, "insertIntoGeekTime2");
    }

}
