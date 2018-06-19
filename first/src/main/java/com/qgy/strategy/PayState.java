package com.qgy.strategy;

/**
 * @Author: qgy
 * @Date: 2018/3/16 10:03
 * @Description:
 */
public class PayState {
    private int code;
    private Object data;
    private String msg;

    public  PayState (int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public String toString(){
        return  ("支付状态：[" + code + "]," + msg + ",交易详情：" + data);
    }
}
