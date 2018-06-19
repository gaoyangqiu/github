package com.qgy.template;

import java.sql.ResultSet;

/**
 * @Author: qgy
 * @Date: 2018/3/16 15:13
 * @Description:
 */
public interface RowMapper<T> {
    public T mapRow(ResultSet rs,int rowNum) throws Exception;
}
