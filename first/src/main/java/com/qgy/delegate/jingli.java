package com.qgy.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qgy
 * @Date: 2018/3/21 11:33
 * @Description:
 */
public class jingli implements ITarget{
    Map <String,ITarget> map=new HashMap<String,ITarget>();

    public jingli (){
        map.put("做饭",new WorkA());
        map.put("工作",new WorkB());
    }
    @Override
    public void doingSomething(String commond) {
    map.get(commond).doingSomething(commond);
    }
}
