package demo.rpcfx.scan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScanConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String basePackages = "demo.rpcfx.api";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RemoteApiScan scanner = new RemoteApiScan(registry);
        scanner.registerFilters();
        scanner.scan(basePackages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory registry) throws BeansException {

    }

}
