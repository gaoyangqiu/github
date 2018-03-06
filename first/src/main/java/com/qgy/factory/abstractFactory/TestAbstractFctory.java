package com.qgy.factory.abstractFactory;

/**
 * @Author: qgy
 * @Date: 2018/3/6 11:39
 * @Description:
 */
public class TestAbstractFctory {
    public static void main(String[] args) {
        AbstractFactory factory=new AbstractFactoryImpl();
        System.out.println(factory.getMengniu());
        System.out.println(factory.getYili());
    }
}
