package com.mybatis;

import com.beans.Test;

/**
 * @Author: qgy
 * @Date: 2018/4/16 15:59
 * @Description:
 */
public class MybatisTest {
    public static void main(String[] args) {
        SqlSession sqlSession=new SqlSession(new SimpleExecutor(),new MyConfiguration());
        TestMapper testMapper=sqlSession.getMapper(TestMapper.class);
        Test test=testMapper.selectByPrimaryKey(1);
        System.out.println(test);
    }
}
