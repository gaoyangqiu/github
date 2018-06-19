package com.qgy.decorator;

/**
 * @Author: qgy
 * @Date: 2018/3/21 17:37
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA md11 = new ManDecoratorA();
        ManDecoratorB md22 = new ManDecoratorB();
        md11.setPerson(man);
        md22.setPerson(md11);
        md22.eat();
    }
}
