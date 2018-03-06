package com.qgy.factory.factory;

import com.qgy.factory.Milk;
import com.qgy.factory.Yili;

/**
 * @Author: qgy
 * @Date: 2018/3/6 11:12
 * @Description:
 */
public class YiliMilkFactory implements MilkFactory {
    public Milk getMilk() {
        return new Yili();
    }
}
