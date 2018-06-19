package com.qgy.single;

import com.sun.org.apache.xml.internal.security.Init;

/**
 * @Author: qgy
 * @Date: 2018/3/15 11:11
 * @Description:
 */
public class InitSinger {
    private InitSinger(){}
    private static class singleHolder{
        private static InitSinger initSinger=new InitSinger(){};
    }
    public static InitSinger getInstance(){
        return singleHolder.initSinger;
    }

}
