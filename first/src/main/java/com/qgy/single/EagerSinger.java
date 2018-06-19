package com.qgy.single;

/**
 * @Author: qgy
 * @Date: 2018/3/8 11:00
 * @Description:
 */
public class EagerSinger {

    private static  EagerSinger eagerInstance=new EagerSinger();

    private EagerSinger(){}

    public static EagerSinger getEagerInstance() {
        return eagerInstance;
    }
}
