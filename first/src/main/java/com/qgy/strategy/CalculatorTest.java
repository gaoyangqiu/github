package com.qgy.strategy;

/**
 * @Author: qgy
 * @Date: 2018/3/22 19:14
 * @Description:
 */
public class CalculatorTest {
    public static void main(String[] args) {
        System.out.println(Calculator.add.getIstrategy().excute(1,2));
        System.out.println( Calculator.sub.getIstrategy().excute(3,1));


    }
}
