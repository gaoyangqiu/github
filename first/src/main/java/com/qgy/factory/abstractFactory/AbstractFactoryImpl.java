package com.qgy.factory.abstractFactory;

import com.qgy.factory.Mengniu;
import com.qgy.factory.Milk;
import com.qgy.factory.Yili;

/**
 * @Author: qgy
 * @Date: 2018/3/6 11:38
 * @Description:
 */
public class AbstractFactoryImpl extends AbstractFactory {
    public Milk getMengniu() {
        return new Mengniu();
    }

    public Milk getYili() {
        return new Yili();
    }
}
