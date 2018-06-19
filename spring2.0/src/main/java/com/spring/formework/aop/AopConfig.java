package com.spring.formework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qgy
 * @Date: 2018/5/30 15:39
 * @Description:
 */
public class AopConfig {

    private Map<Method,Aspect> points=new HashMap<Method, Aspect>();

    public void put(Method target,Object aspect,Method[] points){
        this.points.put(target,new Aspect(aspect,points));
    }
    public Aspect get(Method method){
        return this.points.get(method);
    }
    public boolean contains(Method method){
        return this.points.containsKey(method);
    }

    public class  Aspect{
        private Object aspectObject;
        private Method[] points;
        public Aspect(Object aspectObject,Method[] points){
            this.aspectObject=aspectObject;
            this.points=points;
        }

        public Object getAspectObject() {
            return aspectObject;
        }

        public void setAspectObject(Object aspectObject) {
            this.aspectObject = aspectObject;
        }

        public Method[] getPoints() {
            return points;
        }

        public void setPoints(Method[] points) {
            this.points = points;
        }
    }

}
