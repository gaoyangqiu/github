package com.qgy.delegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: qgy
 * @Date: 2018/3/17 17:14
 * @Description:
 */
public class ServletDispatcher {
    private List<Handler> handlerMapping=new ArrayList<Handler>();

    public void doSerivce(HttpServletRequest request, HttpServletResponse response){

    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response){
        //获取用户请求
        String uri=request.getRequestURI();
        Handler handler=null;
        for(Handler h:handlerMapping){
            if (uri.equals(h.getUrl())){
                handler=h;
                break;
            }
        }
        Object object =null;
        try {
            object =handler.getMethod().invoke(handler.getController(),request.getParameter("test"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    class Handler{
        private Object Controller;
        private Method method;
        private String url;

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getController() {
            return Controller;
        }

        public void setController(Object controller) {
            Controller = controller;
        }
    }
}
