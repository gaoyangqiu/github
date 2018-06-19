package com.spring.formework.context.support;

import com.spring.formework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: qgy
 * @Date: 2018/5/24 20:44
 * @Description:
 */
public class BeanDefinitionReader {
    private Properties config=new Properties();
    private List<String> registyBeanClasses=new ArrayList<String>();

    private final  String SCAN_PACKAGE="scanPackage";

    public BeanDefinitionReader (String... locations){
        InputStream is=this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));

        try {
            config.load(is);
        } catch (IOException e) {
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
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String property) {

        URL url = this.getClass().getClassLoader().getResource("/" + property.replaceAll("\\.","/"));

        File classDir=new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()){
                doScanner(property+"."+file.getName());
            }else{
                registyBeanClasses.add(property+"."+file.getName().replace(".class",""));
            }
        }
    }
    public BeanDefinition registerBean(String className){
        if(this.registyBeanClasses.contains(className)){
            BeanDefinition beanDefinition=new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf("."))+1));
            return beanDefinition;
        }
        return  null;
    }

    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public List<String> loadBeanDefinitions(){
        return   registyBeanClasses;
    }

    public Properties getConfig(){
        return this.config;
    }

}
