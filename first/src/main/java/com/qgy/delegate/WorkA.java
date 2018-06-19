package com.qgy.delegate;

/**
 * @Author: qgy
 * @Date: 2018/3/21 11:33
 * @Description:
 */
public class WorkA implements ITarget {
    @Override
    public void doingSomething(String commond) {
        System.out.println("我是A，我在"+commond);
    }
}
