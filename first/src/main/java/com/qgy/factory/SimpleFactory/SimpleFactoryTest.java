package com.qgy.factory.SimpleFactory;

public class SimpleFactoryTest {

    public static void main(String[] args) {
    SimpleFactory factory=new SimpleFactory();
        System.out.println( factory.getMilk("蒙牛").getName());
    }
}
