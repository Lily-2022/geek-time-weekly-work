package geek.time.weekly.work.week5.starter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties(prefix="ischool.demo")
@Getter
@Setter
public class SpringBootPropertiesConfigurations {

    private Properties props = new Properties();

}
