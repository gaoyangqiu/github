package com.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * @Author: qgy
 * @Date: 2018/4/21 17:32
 * @Description:
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
