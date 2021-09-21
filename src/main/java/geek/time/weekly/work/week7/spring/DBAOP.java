package geek.time.weekly.work.week7.spring;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBAOP {

    @Pointcut("@annotation(geek.time.weekly.work.week7.spring.GenericDS)")
    public void pointCut() {};

    @Before(value="pointCut() && @annotation(genericDS)", argNames = "genericDS")
    public void before(Joinpoint joinpoint, GenericDS genericDS) {
        String dbname = genericDS.value();
        CustomerContextAdaptor.setCustomerType(dbname);
    }

}
