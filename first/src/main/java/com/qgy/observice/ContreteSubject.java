package com.qgy.observice;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qgy
 * @Date: 2018/3/22 11:22
 * @Description:
 */
public class ContreteSubject implements Subject {

    List<Observice> observices=new ArrayList<>();

    @Override
    public void addObservice(Observice observice) {
            if (observice ==null){
                throw new NullPointerException("observice==null");
            }
            if(!observices.contains(observice)){
                observices.add(observice);
            }
    }

    @Override
    public void removeObservice(Observice observice) {
            observices.remove(observice);
    }

    @Override
    public void removeAllObservice() {
        observices.removeAll(observices);
    }

    @Override
    public void notifyAllObservice(Object data) {
        for (Observice observice : observices) {
            observice.update(this,data);
        }
    }

    @Override
    public void notify(Observice observice, Object data) {
        if (observice!=null){
            observice.update(this,data);
        }

    }
}
