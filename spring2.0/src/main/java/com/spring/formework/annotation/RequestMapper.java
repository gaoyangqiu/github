package com.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * @Author: qgy
 * @Date: 2018/4/21 17:32
 * @Description:
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapper {
    String value() default "";
}
