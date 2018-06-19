package com.service;

/**
 * @Author: qgy
 * @Date: 2018/5/24 11:54
 * @Description:
 */
public class DemoService implements IDemoService {
    @Override
    public String get(String name) {
        return "My name is " + name;
    }
}
