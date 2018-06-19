package com.spring.formework.context;

import com.spring.formework.annotation.AutoWried;
import com.spring.formework.annotation.Controller;
import com.spring.formework.annotation.Service;
import com.spring.formework.aop.AopConfig;
import com.spring.formework.beans.BeanDefinition;
import com.spring.formework.beans.BeanPostProcessor;
import com.spring.formework.beans.BeanWrapper;
import com.spring.formework.context.support.BeanDefinitionReader;
import com.spring.formework.core.BeanFactory;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: qgy
 * @Date: 2018/5/24 20:34
 * @Description:
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory{

    private String [] configLocations;

    private BeanDefinitionReader beanDefinitionReader;

    private Map<String,Object> beanCacheMap=new HashMap<String,Object>();

    private Map<String,BeanWrapper> beanWrapperMap=new ConcurrentHashMap<String,BeanWrapper>();


    public ApplicationContext(String ... configLocations){
        this.configLocations=configLocations;
        refresh();
    }

    public void refresh(){
        this.beanDefinitionReader=new BeanDefinitionReader(configLocations);

        List<String> beanDefinitions= beanDefinitionReader.loadBeanDefinitions();

        doRegistry(beanDefinitions);

        doAutowired();


    }

    private void doAutowired() {

        for (Map.Entry<String,BeanDefinition> stringBeanDefinitionEntry : this.beanDefinitionMap.entrySet()) {
            String beanName=stringBeanDefinitionEntry.getKey();

            if(!stringBeanDefinitionEntry.getValue().isLazyInit()){
                Object obj=getBean(beanName);
            }
        }

        for (Map.Entry<String,BeanWrapper> beanWrapperEntry : this.beanWrapperMap.entrySet()) {
            populateBean(beanWrapperEntry.getKey(),beanWrapperEntry.getValue().getOriginalInstance());

        }
    }

    private void populateBean(String beanName, Object instance) {

        Class clazz=instance.getClass();
        if (!clazz.isAnnotationPresent(Controller.class)||clazz.isAnnotationPresent(Service.class)){
            return;
        }
        Field[] fields =clazz.getDeclaredFields();
        for (Field field : fields) {
            if(!field.isAnnotationPresent(AutoWried.class)){
                continue;
            }
            AutoWried autoWried=field.getAnnotation(AutoWried.class);
            String autowriredBeanName= autoWried.value().trim();
            if ("".equals(autowriredBeanName)){
                autowriredBeanName= field.getType().getName();
            }
            field.setAccessible(true);
            try {
                field.set(instance,this.beanWrapperMap.get(autowriredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void doRegistry(List<String> beanDefinitions) {

        try {

            for (String className : beanDefinitions) {
                Class<?> beanClass = Class.forName(className);

                if (beanClass.isInterface()) {
                    continue;
                }

                BeanDefinition beanDefinition = beanDefinitionReader.registerBean(className);

                if (beanDefinition != null) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }

                Class[] interfaces = beanClass.getInterfaces();
                for (Class i : interfaces) {
                    this.beanDefinitionMap.put(i.getName(), beanDefinition);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition=this.beanDefinitionMap.get(beanName);
        String className=beanDefinition.getBeanClassName();

        try {
            BeanPostProcessor beanPostProcessor=new BeanPostProcessor();

            Object instance=instantionBean(beanDefinition);

            if (null==instance){return null;}

            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
            BeanWrapper beanWrapper=new BeanWrapper(instance);
            beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));
            beanWrapper.setPostProcessor(beanPostProcessor);
            this.beanWrapperMap.put(beanName,beanWrapper);

            beanPostProcessor.postProcessAfterInitialization(instance,beanName);

            return this.beanWrapperMap.get(beanName).getWrapperInstance();

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Object instantionBean(BeanDefinition beanDefinition) {
        Object instance=null;
        String className=beanDefinition.getBeanClassName();

        try {
            if (this.beanCacheMap.containsKey(className)) {
                instance = this.beanCacheMap.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className, instance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }


    private AopConfig instantionAopConfig(BeanDefinition beanDefinition) throws Exception{
        AopConfig config=new AopConfig();
        String expression=beanDefinitionReader.getConfig().getProperty("pointCut");
        String[] before=beanDefinitionReader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after=beanDefinitionReader.getConfig().getProperty("aspectAfter").split("\\s");

        String claaaName=beanDefinition.getBeanClassName();
        Class<?> clazz=Class.forName(claaaName);
        Pattern pattern=Pattern.compile(expression);
        Class aspectClass=Class.forName(before[0]);

        for (Method method : clazz.getMethods()) {
            Matcher matcher=pattern.matcher(method.toString());
            if (matcher.matches()){
                config.put(method,aspectClass.newInstance(),
                        new Method[]{aspectClass.getMethod(before[1]),aspectClass.getMethod(after[1])});
            }
        }
        return config;
    }

    public String[] getBeanDefinitonNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitioncount(){
        return this.beanDefinitionMap.size();
    }


    public Properties getConfig(){
        return this.beanDefinitionReader.getConfig();
    }
}
