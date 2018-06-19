package com.qgy.strategy;

/**
 * @Author: qgy
 * @Date: 2018/3/16 15:03
 * @Description:
 */
public class PayStrategyTest {
    public static void main(String[] args) {

        Order order=new Order("2","test2",150.0);
        System.out.println(order.pay(PayType.Wechat_pay));
    }
}
