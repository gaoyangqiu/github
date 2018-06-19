package com.qgy.decorator;

/**
 * @Author: qgy
 * @Date: 2018/3/21 16:36
 * @Description:
 */
public class Man implements  Person {
    @Override
    public void eat() {
        System.out.println("男人吃饭");
    }
}
