package cn.bugstack.springframework.tx.transaction.annotation;

import java.lang.annotation.*;

/**
 * @author zhangdd on 2022/2/26
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Transactional {

    Class<? extends Throwable>[] rollbackFor() default {};
}
