package com.qgy.single;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: qgy
 * @Date: 2018/3/15 15:19
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<?> classType=Elvis.class;
    Constructor<?> c=classType.getDeclaredConstructor(null);
    c.setAccessible(true);
    Elvis e1=(Elvis) c.newInstance();
    Elvis e2=Elvis.getInstance();
        System.out.println(e1=e2);
    }
}
