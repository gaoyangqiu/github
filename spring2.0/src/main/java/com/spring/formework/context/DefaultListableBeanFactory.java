package com.spring.formework.context;

import com.spring.formework.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: qgy
 * @Date: 2018/5/24 20:35
 * @Description:
 */
public abstract class DefaultListableBeanFactory extends AbstractApplicationContext{

    protected Map<String,BeanDefinition> beanDefinitionMap =new ConcurrentHashMap<String,BeanDefinition>();
    protected  void onRefresh(){

    }

    @Override
    protected void refreshBeanFactory() {

    }
}
