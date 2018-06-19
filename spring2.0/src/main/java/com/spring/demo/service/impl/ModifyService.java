package com.spring.demo.service.impl;

import com.spring.demo.service.IModifyService;

/**
 * @Author: qgy
 * @Date: 2018/6/15 11:23
 * @Description:
 */
public class ModifyService implements IModifyService{
    @Override
    public String add(String name, String addr) {
        return "modifyService add,name=" + name + ",addr=" + addr;
    }

    @Override
    public String edit(Integer id, String name) {
        return "modifyService edit,id=" + id + ",name=" + name;
    }

    @Override
    public String remove(Integer id) {
        return "modifyService id=" + id;
    }
}
