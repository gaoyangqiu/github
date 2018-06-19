package com.spring.formework.beans;

import com.spring.formework.aop.AopConfig;
import com.spring.formework.aop.AopProxy;

/**
 * @Author: qgy
 * @Date: 2018/5/24 20:39
 * @Description:
 */
public class BeanWrapper {
    private BeanPostProcessor postProcessor;
    private Object wrapperInstance;
    private AopProxy aopProxy=new AopProxy();
    private Object originalInstance;

    public BeanWrapper(Object instance){
        this.wrapperInstance=aopProxy.getProxy(instance);
        this.originalInstance=instance;
    }

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public Class<?> getWrapperClass(){
        return this.wrapperInstance.getClass();
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public AopProxy getAopProxy() {
        return aopProxy;
    }

    public void setAopProxy(AopProxy aopProxy) {
        this.aopProxy = aopProxy;
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }

    public void setOriginalInstance(Object originalInstance) {
        this.originalInstance = originalInstance;
    }
    public void setAopConfig(AopConfig aopConfig){
        aopProxy.setAopConfg(aopConfig);
    }

}
