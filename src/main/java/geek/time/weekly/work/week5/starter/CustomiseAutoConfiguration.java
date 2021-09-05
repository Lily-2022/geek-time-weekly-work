package geek.time.weekly.work.week5.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("")
@EnableConfigurationProperties()
@AutoConfigureBefore
@RequiredArgsConstructor
public class CustomiseAutoConfiguration implements EnvironmentAware {

    private String schemaName;
    private final SpringBootPropertiesConfigurations props;


    @Bean
    public ModeConfiguration modeConfiguration() {
        return null == props.getMode() ? null : new ModeConfigurationYamlSwapper().swapToObject(props.getMode());
    }

    @Override
    public void setEnvironment(Environment environment) {
        schemaName = SchemaNameSetter.getSchemaName(environment);
    }
}
