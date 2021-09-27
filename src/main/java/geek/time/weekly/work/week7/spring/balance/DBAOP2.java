package geek.time.weekly.work.week7.spring.balance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class DBAOP2 {

    @Autowired
    DatasourceAdaptor datasourceAdaptor;

    @Pointcut("@annotation(geek.time.weekly.work.week7.spring.balance.GenericDS)")
    public void pointCut() {};

    @Around("pointCut()")
    public List<Map<String, Object>> setDatabaseSource(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("data source change ....");
        Object[] argv = joinPoint.getArgs();
        argv[0] = datasourceAdaptor.getSlaveDataSource();
        Arrays.stream(argv).count();
        return  (List<Map<String, Object>>) joinPoint.proceed(argv);
    }


}
