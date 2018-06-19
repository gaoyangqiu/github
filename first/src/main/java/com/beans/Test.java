package com.beans;

/**
 * @Author: qgy
 * @Date: 2018/4/11 11:24
 * @Description:
 */
public class Test {
    private Integer id;

    private Integer num;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
