package com.mybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: qgy
 * @Date: 2018/4/2 16:40
 * @Description:
 */
public class MapperProxy implements InvocationHandler {
    private SqlSession sqlsession;
    public MapperProxy(SqlSession sqlsession){
        this.sqlsession=sqlsession;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(MyConfiguration.TestMapperXml.namespace)){
            String sql=MyConfiguration.TestMapperXml.methodSqlMapping.get(method.getName());
            return sqlsession.selectOne(sql,String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
