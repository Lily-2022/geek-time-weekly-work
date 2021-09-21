package geek.time.weekly.work.week7.spring;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenericDS {
    String value() default "geek_time";
}
