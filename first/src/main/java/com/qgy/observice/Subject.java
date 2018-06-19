package com.qgy.observice;

/**
 * @Author: qgy
 * @Date: 2018/3/22 11:12
 * @Description:
 */
public interface Subject {

    void addObservice(Observice observice);


    void removeObservice(Observice observice);


    void removeAllObservice();

    void notifyAllObservice(Object data);

    void notify(Observice observice, Object data);
}
