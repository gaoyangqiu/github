package com.qgy.factory.factory;

import com.qgy.factory.Mengniu;
import com.qgy.factory.Milk;

/**
 * @Author: qgy
 * @Date: 2018/3/6 11:11
 * @Description:
 */
public class MengniuMilkFactory  implements MilkFactory{

    public Milk getMilk() {
        return new Mengniu();
    }
}
