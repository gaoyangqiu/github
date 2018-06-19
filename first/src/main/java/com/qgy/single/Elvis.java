package com.qgy.single;


import sun.security.jca.GetInstance;

/**
 * @Author: qgy
 * @Date: 2018/3/15 15:38
 * @Description:
 */
public class Elvis {
    private  static  boolean flag=false;
    private Elvis(){
        synchronized (Elvis.class){
            System.out.println("try to instance");
            if (flag==false){
                System.out.println(" first time instance");
                flag =!flag;
            }else{
                throw new RuntimeException("单例模式被侵犯");
            }
        }
    }
    private static class ElvisHolder{
        private static final  Elvis elvis =new Elvis();
    }
    public static  Elvis getInstance(){
        System.out.println("in getInstance");
        return  ElvisHolder.elvis;
    }
}
