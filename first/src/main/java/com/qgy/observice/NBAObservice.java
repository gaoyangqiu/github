package com.qgy.observice;

/**
 * @Author: qgy
 * @Date: 2018/3/22 14:23
 * @Description:
 */
public class NBAObservice implements Observice {
    @Override
    public void update(Subject subject, Object data) {
        System.out.println("我是"+this.getClass().getSimpleName()+","+data+"别看NBA了");
    }
}
