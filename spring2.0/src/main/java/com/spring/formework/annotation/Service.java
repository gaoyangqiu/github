package com.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * @Author: qgy
 * @Date: 2018/4/21 17:32
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
