package com.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * @Author: qgy
 * @Date: 2018/4/21 17:26
 * @Description:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoWried {
    String value() default "";
}
