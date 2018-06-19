package com.spring.servlet;

import com.spring.annotation.AutoWried;
import com.spring.annotation.Controller;
import com.spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: qgy
 * @Date: 2018/4/21 15:43
 * @Description:
 */
public class DiapatchServlet extends HttpServlet {

    private Properties contextConfig=new Properties();
    private Map<String,Object> beanMap=new ConcurrentHashMap<>();
    private List<String> className=new ArrayList<String>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(",,,,,,调用post........");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //开始初始化进程
        //定位
        doLodConfig(config.getInitParameter("contextConfigLocaltion"));
        //加载
        doScanner(contextConfig.getProperty("scanPackage"));

        //注解
        doRegistry();

        //自动依赖注入
        //在spring中是通过调用getBean方法才依赖注入的
        doAutowiered();

        iniHandlerMapping();
    }

    private void iniHandlerMapping() {
    }
    private void doRegistry() {
        if(className.isEmpty()){return;}
        try{
            for (String className : className) {
                Class<?> clazz=Class.forName(className);

                if(clazz.isAnnotationPresent(Controller.class)){
                    String beanName=lowerFirstCase(clazz.getSimpleName());
                    beanMap.put(beanName, clazz.newInstance());
                }else if(clazz.isAnnotationPresent(Service.class)){
                    Service service=clazz.getAnnotation(Service.class);
                    String beanName=service.value();
                    if("".equals(beanName.trim())){
                        beanName=lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance=clazz.newInstance();
                    beanMap.put(beanName,instance);
                    Class<?>[] interfaces =clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        beanMap.put(i.getName(),instance);
                    }
                }else{
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doAutowiered() {
        if (beanMap.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry : beanMap.entrySet()) {
            Field[] fields=entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(AutoWried.class)){
                    continue;
                }
                AutoWried autoWried=field.getAnnotation(AutoWried.class);
                String beanName=autoWried.value().trim();
                if ("".equals(beanName)){
                    field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage) {
        URL url=this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File classDir=new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());
            }else{
                className.add(scanPackage+"."+file.getName().replace(".class",""));
            }
        }

    }

    private void doLodConfig(String contextConfigLocaltion) {
        InputStream is=this.getClass().getClassLoader().getResourceAsStream(contextConfigLocaltion.replace("classpath:",""));
        try {
            contextConfig.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null!=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private String lowerFirstCase(String str){
        char[] chars=str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

}
