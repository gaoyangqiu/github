package com.qgy.decorator;

/**
 * @Author: qgy
 * @Date: 2018/3/21 17:32
 * @Description:
 */
public class ManDecoratorA extends Decorator {
    public void eat(){
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }
    public void reEat(){
        System.out.println("在吃一顿");
    }
}
