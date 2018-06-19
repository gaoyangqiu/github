package com.qgy.delegate;

/**
 * @Author: qgy
 * @Date: 2018/3/21 11:34
 * @Description:
 */
public class WorkB implements ITarget {
    @Override
    public void doingSomething(String commond) {
        System.out.println("我是B，我在"+commond);
    }
}
