package com.spring.formework.beans;

/**
 * @Author: qgy
 * @Date: 2018/5/24 20:39
 * @Description:
 */
public class BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean,String beanName){

        return  bean;
    }

    public Object postProcessAfterInitialization(Object bean,String beanName){
        return bean;
    }
}
