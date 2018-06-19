package com.qgy.strategy;

/**
 * @Author: qgy
 * @Date: 2018/3/16 14:43
 * @Description:
 */
public enum PayType {
    Ali_pay(new AliPay()),
    Wechat_pay(new WechatPay());
    private Payment payment;
    PayType(Payment payment){
        this.payment=payment;
    }
    public Payment get(){
        return  this.payment;
    }
}
