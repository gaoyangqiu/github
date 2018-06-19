package com.spring.demo.service.impl;

import com.spring.demo.service.IQueryService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: qgy
 * @Date: 2018/6/15 11:24
 * @Description:
 */
public class QueryService implements IQueryService {
    @Override
    public String query(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String json = "{name:\"" + name + "\",time:\"" + time + "\"}";
        return json;

    }
}
