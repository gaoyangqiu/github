package com.qgy.strategy;

/**
 * @Author: qgy
 * @Date: 2018/3/22 17:43
 * @Description:
 */
public class Add implements Istrategy {
    @Override
    public int excute(int a, int b) {
        return a+b;
    }
}
