package com.qgy.single;

/**
 * @Author: qgy
 * @Date: 2018/3/15 15:06
 * @Description:
 */
public enum EnumSinger {
    INSTANCE;

    private String name;
    public void test() {
        System.out.println("The Test!");
    }

    public void setName(String name){

        this.name= name;
    }

    public String getName(){

        return name;
    }
}
