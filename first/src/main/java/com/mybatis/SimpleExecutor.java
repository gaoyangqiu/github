package com.mybatis;

import com.beans.Test;

import java.sql.*;

/**
 * @Author: qgy
 * @Date: 2018/4/11 11:21
 * @Description:
 */
public class SimpleExecutor implements MyExecutor {
    @Override
    public <T> T query(String statement, String parameter) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        Test test=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://118.25.24.140:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root1", "root1");
        String sql=String.format(statement,Integer.parseInt(parameter));
        preparedStatement=connection.prepareStatement(sql);
        ResultSet rs=preparedStatement.executeQuery();
        while(rs.next()){
            test=new Test();
            test.setId(rs.getInt(1));
            test.setNum(rs.getInt(2));
            test.setName(rs.getString(3));
        }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null!=connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return (T)test;
    }
}
