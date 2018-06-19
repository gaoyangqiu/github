package com.spring.formework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: qgy
 * @Date: 2018/5/30 15:39
 * @Description:
 */
public class AopProxy implements InvocationHandler {


    private AopConfig config;
    private Object target;

    public Object getProxy(Object instance){
        this.target =instance;
        Class<?> clazz=instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public void setAopConfg(AopConfig config){
        this.config=config;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m=this.target.getClass().getMethod(method.getName(),method.getParameterTypes());
        if(config.contains(m)){
            AopConfig.Aspect aspect=config.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspectObject());
        }
        Object obj=method.invoke(this.target,args);
        if (config.contains(m)){
            AopConfig.Aspect aspect=config.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspectObject());
        }
        return obj;
    }
}
