package com.qgy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestJdk {
    public static void main(String[] args) {
        Qiugaoyang yangyang=new Qiugaoyang();
        Person person=(Person)new Meipo().getinstance(yangyang);
        System.out.println(person.getClass());
        person.findLove();
        byte[] data= ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
        try {
            FileOutputStream os=new FileOutputStream("D:/qgy/github/$Proxy0.class");
            os.write(data);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
