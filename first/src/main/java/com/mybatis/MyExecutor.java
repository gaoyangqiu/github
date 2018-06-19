package com.mybatis;

/**
 * @Author: qgy
 * @Date: 2018/4/2 16:11
 * @Description:
 */
public interface MyExecutor {
    <T> T query(String statement, String parameter);
}
