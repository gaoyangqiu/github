package com.qgy.strategy;

/**
 * @Author: qgy
 * @Date: 2018/3/16 14:37
 * @Description:
 */
public interface Payment {
    public PayState pay(String uid, double amount);
}
