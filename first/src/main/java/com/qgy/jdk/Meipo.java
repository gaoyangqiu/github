package com.qgy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Meipo implements InvocationHandler{

    private Person target;
    public Object getinstance(Person target){
        this.target=target;
        Class tatgetClass=target.getClass();
        Object proxy=Proxy.newProxyInstance(tatgetClass.getClassLoader(),tatgetClass.getInterfaces(),this);
        return proxy;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆");
        System.out.println("开始海选");
        target.findLove();
        System.out.println("寻亲结束");
        return null;
    }
}
