package com.qgy.strategy;

/**
 * @Author: qgy
 * @Date: 2018/3/22 17:44
 * @Description:
 */
public enum  Calculator {
    add(new Add()),
    sub(new Sub());

    private Istrategy istrategy;
    Calculator(Istrategy istrategy ){
        this.istrategy=istrategy;
    };

    public Istrategy getIstrategy() {
        return istrategy;
    }
}
