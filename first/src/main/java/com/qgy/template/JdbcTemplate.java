package com.qgy.template;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qgy
 * @Date: 2018/3/16 15:16
 * @Description:
 */
public class JdbcTemplate {
    private DataSource dataSource;
    public JdbcTemplate(DataSource dataSource){
        this.dataSource=dataSource;
    }
    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
    private PreparedStatement createPreparedStatement(Connection connection,String sql) throws SQLException {
        return  connection.prepareStatement(sql);
    }
    private ResultSet executeQuery(PreparedStatement pstmt, Object[] values) throws SQLException {
        for( int i=0;i<values.length;i++){
            pstmt.setObject(i,values[i]);
        }
        return pstmt.executeQuery();
    }
    private void closeConnection(Connection conn) throws  Exception{
        //通常把它放到连接池回收
    }

    private List<?> parseResultSet(ResultSet rs,RowMapper rowMapper) throws Exception {
        List<Object> result=new ArrayList<Object>();
        int rowNum=1;
        while (rs.next()){
            result.add(rowMapper.mapRow(rs,rowNum++));
        }
        return result;
    }
    private void closeStatement(Statement stmt) throws  Exception{
        stmt.close();
    }

    private void closeResultSet(ResultSet rs) throws  Exception{
        rs.close();
    }

    public List<?> executeQuery(String sql, RowMapper<?> rowMapper,Object[]values) throws Exception{
        //1.获取连接
        Connection conn=this.getConnection();
        //2.创建语句集
        PreparedStatement pstmt=this.createPreparedStatement(conn,sql);
        //3.执行语句集，并且获得结果集
        ResultSet rs=this.executeQuery(pstmt,values);
        //4.解析语句集
        List <?> result=this.parseResultSet(rs,rowMapper);
        //5.关闭结果集
        this.closeResultSet(rs);
        //6.关闭语句集
        this.closeStatement(pstmt);
        //7.关闭连接
        this.closeConnection(conn);
        return result;
    }
}
