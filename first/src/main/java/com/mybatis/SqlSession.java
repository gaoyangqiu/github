package com.mybatis;



/**
 * @Author: qgy
 * @Date: 2018/4/2 16:09
 * @Description:
 */
public class SqlSession {
    private MyExecutor executor;
    private MyConfiguration configuration;

    public SqlSession (MyExecutor executor,MyConfiguration configuration){
        this.configuration=configuration;
        this.executor=executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }
    public <T> T selectOne(String statement,String parameter){
        return executor.query(statement,parameter);
    }
}
