package com.mybatis;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qgy
 * @Date: 2018/4/2 16:15
 * @Description:
 */
public class MyConfiguration {
    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {

        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new MapperProxy(sqlSession));
    }

    static  class TestMapperXml{
        public  static final String namespace="com.mybatis.TestMapper";
        public static final Map<String,String>  methodSqlMapping=new HashMap<>();
        static{
            methodSqlMapping.put("selectByPrimaryKey","select * from user where id =%d");
        }
    }
}
