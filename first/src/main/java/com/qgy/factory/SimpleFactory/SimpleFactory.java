package com.qgy.factory.SimpleFactory;

import com.qgy.factory.Mengniu;
import com.qgy.factory.Milk;
import com.qgy.factory.Yili;

public class SimpleFactory {

    public Milk getMilk(String name){
        if ("伊利".equals(name)){
            return new Yili();
        }else if("蒙牛".equals(name)){
            return new Mengniu();
        }else {
        return null;}
    }
}
