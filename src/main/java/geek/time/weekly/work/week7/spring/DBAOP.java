package geek.time.weekly.work.week7.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Aspect
@Component
public class DBAOP {

    @Autowired
    DatasourceAdaptor datasourceAdaptor;

    @Pointcut("@annotation(geek.time.weekly.work.week7.spring.GenericDS)")
    public void pointCut() {};

    @Around("pointCut")
    public List<Map<String, Object>> setDatabaseSource(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] argv = joinPoint.getArgs();
        argv[0] = datasourceAdaptor.getSlaveDataSource();
        return  (List<Map<String, Object>>) joinPoint.proceed(argv);
    }



}
