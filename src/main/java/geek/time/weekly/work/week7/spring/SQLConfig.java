package geek.time.weekly.work.week7.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@ComponentScan("geek.time.weekly.work.week7.spring")
@Configuration
@EnableAspectJAutoProxy
public class SQLConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource createDataSourceGeekTime() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/geek_time?rewriteBatchedStatements=true&characterEncoding=UTF-8&useUnicode=true&allowMultiQueries=true");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("Mysql_2021");
        hikariConfig.setMaximumPoolSize(200);
        hikariConfig.setConnectionTimeout(30 * 1000);
        hikariConfig.setConnectionTestQuery("select 1");

        return new HikariDataSource(hikariConfig);
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource createDataSourceGeekTime2() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/geek_time2?rewriteBatchedStatements=true&characterEncoding=UTF-8&useUnicode=true&allowMultiQueries=true");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("Mysql_2021");
        hikariConfig.setMaximumPoolSize(200);
        hikariConfig.setConnectionTimeout(30 * 1000);
        hikariConfig.setConnectionTestQuery("select 1");

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource data = new DynamicDataSource();
        DataSource geekTimeSource = createDataSourceGeekTime();
        DataSource geekTime2Source = createDataSourceGeekTime2();

        Map map = new HashMap();
        map.put("geek_time", geekTimeSource);
        map.put("geek_time2", geekTime2Source);
        data.setTargetDataSources(map);
        data.setDefaultTargetDataSource(geekTimeSource);
        return data;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dynamicDataSource());
        return template;
    }


}
