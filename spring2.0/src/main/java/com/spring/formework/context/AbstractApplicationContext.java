package com.spring.formework.context;

/**
 * @Author: qgy
 * @Date: 2018/5/24 20:34
 * @Description:
 */
public abstract class AbstractApplicationContext {
    protected  void onRefresh(){

    }
    protected  abstract  void refreshBeanFactory();
}
