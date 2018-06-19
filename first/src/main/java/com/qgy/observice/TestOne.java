package com.qgy.observice;

/**
 * @Author: qgy
 * @Date: 2018/3/22 14:27
 * @Description:
 */
public class TestOne {
    public static void main(String[] args) {
        ContreteSubject subject=new ContreteSubject();
        NBAObservice nbaObservice=new NBAObservice();
        CartService cartService=new CartService();
        subject.addObservice(nbaObservice);
        subject.addObservice(cartService);
        subject.notifyAllObservice("老师来了");
    }
}
