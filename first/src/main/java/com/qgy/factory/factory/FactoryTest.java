package com.qgy.factory.factory;

/**
 * @Author: qgy
 * @Date: 2018/3/6 11:14
 * @Description:
 */
public class FactoryTest {
    public static void main(String[] args) {
        MilkFactory factory=new YiliMilkFactory();
        System.out.println(factory.getMilk());
    }
}
