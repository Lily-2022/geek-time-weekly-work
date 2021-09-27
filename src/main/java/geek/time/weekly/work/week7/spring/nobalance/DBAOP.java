package geek.time.weekly.work.week7.spring.nobalance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class DBAOP {

    @Pointcut("@annotation(geek.time.weekly.work.week7.spring.nobalance.GenericDS)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeExecute(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        GenericDS annotation = method.getAnnotation(GenericDS.class);
        if (null == annotation) {
            annotation = joinPoint.getTarget().getClass().getAnnotation(GenericDS.class);
        }
        if (null != annotation) {
            // 切换数据源
            CustomerContextAdaptor.setCustomerType(annotation.value());
        }
    }

    @After("pointcut()")
    public void afterExecute() {
        CustomerContextAdaptor.clear();
    }


}
