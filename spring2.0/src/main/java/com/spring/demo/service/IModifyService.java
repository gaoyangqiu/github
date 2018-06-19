package com.spring.demo.service;

/**
 * @Author: qgy
 * @Date: 2018/6/15 11:19
 * @Description:
 */
public interface IModifyService {

    public String add(String name,String addr);

    public String edit(Integer id,String name);

    public String remove(Integer id);
}
