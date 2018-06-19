package com.qgy.single;

import java.io.Serializable;

/**
 * @Author: qgy
 * @Date: 2018/3/15 15:53
 * @Description:
 */
public class SerSingLeton implements Serializable {
    private static final long serialVersionUid=1l;
    String name;
    private SerSingLeton(){
        System.out.println("Singleton is create");
        name ="Sersingleton";
    }
    private static SerSingLeton instance=new SerSingLeton();
    public static SerSingLeton getInstance(){
        return instance;
    }
    public static void createString(){
        System.out.println("creteString in singleton");
    }
}
