package demo.rpcfx.scan;

import demo.rpcfx.annotation.RemoteApi;
import demo.rpcfx.core.client.Rpcfx;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Set;

public class RemoteApiScan extends ClassPathBeanDefinitionScanner{

    public RemoteApiScan(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
        protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
            return metadataReader.getClassMetadata().isInterface();
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return beanDefinition.getMetadata().isInterface();
        }

        @SneakyThrows
        @Override
        public Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
            processBeanDefinitions(beanDefinitions);
            return beanDefinitions;
        }


        private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitionHolders) throws ClassNotFoundException {
            for (BeanDefinitionHolder holder : beanDefinitionHolders) {

                GenericBeanDefinition beanDefinition = (GenericBeanDefinition)holder.getBeanDefinition();
                // 扫描自定义注解 创建代理类
                Class<?> aClass = Class.forName(beanDefinition.getBeanClassName());
                RemoteApi annotation = aClass.getAnnotation(RemoteApi.class);
                String url = annotation.url();
                if (StringUtils.isEmpty(url)) {
                    throw new RuntimeException("url is null");
                }
                Object o = Rpcfx.create(aClass, url);

                beanDefinition.setBeanClass(o.getClass());
                beanDefinition.setScope("singleton");
                ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
                constructorArgumentValues.addIndexedArgumentValue(0, Proxy.getInvocationHandler(o));
                beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
            }
        }

        public void registerFilters() {
            addIncludeFilter(new AnnotationTypeFilter(RemoteApi.class));
        }

}
