package com.qgy.single;

/**
 * @Author: qgy
 * @Date: 2018/3/10 18:16
 * @Description:
 */
public class LazySinger {

    private static  LazySinger  lazySinger=null;

    private LazySinger(){

    }
    public static synchronized  LazySinger getInstance(){
        if (lazySinger==null){
            lazySinger=new LazySinger();
        }
            return lazySinger;
    }


}
