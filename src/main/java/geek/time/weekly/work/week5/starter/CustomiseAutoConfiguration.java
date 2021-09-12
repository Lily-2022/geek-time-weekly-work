package geek.time.weekly.work.week5.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("")
@EnableConfigurationProperties(SpringBootPropertiesConfigurations.class)
@AutoConfigureBefore
@RequiredArgsConstructor
public class CustomiseAutoConfiguration {

    private String schemaName;

    @Autowired
    private final SpringBootPropertiesConfigurations props;

}
