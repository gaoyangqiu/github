package com.qgy.decorator;

/**
 * @Author: qgy
 * @Date: 2018/3/21 17:35
 * @Description:
 */
public class ManDecoratorB extends Decorator {
    public void eat(){
        super.eat();
        System.out.println("ManDecoratorB类");
    }
}
