package com.qgy.observice;

/**
 * @Author: qgy
 * @Date: 2018/3/22 14:25
 * @Description:
 */
public class CartService implements Observice {
    @Override
    public void update(Subject subject, Object data) {
        System.out.println("我是"+this.getClass().getSimpleName()+","+data+"别看漫画了");
    }
}
