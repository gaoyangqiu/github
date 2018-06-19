package com.spring.formework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: qgy
 * @Date: 2018/6/4 16:39
 * @Description:
 */
public class HandlerAdapter {

    private Map<String,Integer> paramMapping;

    public HandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp,HandlerMapping handler) throws InvocationTargetException, IllegalAccessException {
        Class<?> [] paramType=handler.getMethod().getParameterTypes();
        
        Map<String,String[]> reqParamterMap=req.getParameterMap();
        
        Object[] paramValues=new Object[paramType.length];
        for (Map.Entry<String, String[]> param : reqParamterMap.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]","").replaceAll("\\s","");
            if (!this.paramMapping.containsKey(param.getKey())){continue;}
            int index=this.paramMapping.get(param.getKey());
            paramValues[index]=caseStringValue(value,paramType[index]);

        }
        if(this.paramMapping.containsKey(HttpServletRequest.class.getName())){
            int reqIndex=this.paramMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex]=req;
        }
        if(this.paramMapping.containsKey(HttpServletResponse.class.getName())){
            int respIndex=this.paramMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex]=resp;
        }


        Object result=handler.getMethod().invoke(handler.getController(),paramValues);
        

        if (result==null){
            return null;
        }
        boolean isModeAndView=handler.getMethod().getReturnType()==ModelAndView.class;
        if (isModeAndView){
            return (ModelAndView) result;
        }else{
            return null;
        }
    }

    private Object caseStringValue(String value, Class<?> clazz) {
        if (clazz==String.class){
            return value;
        }else if(clazz==Integer.class){
            return Integer.valueOf(value);
        }else if(clazz==int.class){
            return Integer.valueOf(value).intValue();
        }else{
            return null;
        }
    }
}
