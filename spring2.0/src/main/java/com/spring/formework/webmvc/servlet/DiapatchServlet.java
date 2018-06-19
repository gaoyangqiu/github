package com.spring.formework.webmvc.servlet;



import com.spring.formework.annotation.*;
import com.spring.formework.aop.AopProxyUtils;
import com.spring.formework.context.ApplicationContext;
import com.spring.formework.webmvc.HandlerAdapter;
import com.spring.formework.webmvc.HandlerMapping;
import com.spring.formework.webmvc.ModelAndView;
import com.spring.formework.webmvc.ViewResolver;
import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: qgy
 * @Date: 2018/4/21 15:43
 * @Description:
 */
public class DiapatchServlet extends HttpServlet {

    private final String LOCATION="contextConfigLocation";
    private Properties contextConfig=new Properties();
    private Map<String,Object> beanMap=new ConcurrentHashMap<>();
    private List<String> className=new ArrayList<String>();
    private List<HandlerMapping> handlerMappings=new ArrayList<HandlerMapping>();
    private Map<HandlerMapping,HandlerAdapter> handlerAdapters=new HashMap<HandlerMapping,HandlerAdapter>();
    private List<ViewResolver> viewResolvers=new ArrayList<ViewResolver>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            resp.getWriter().write("<font size='25' color='blue'>500 Exception</font><br/>Details:<br/>" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s","\r\n") +  "<font color='green'><i>Copyright@GupaoEDU</i></font>");
            e.printStackTrace();

        }
        System.out.println(",,,,,,调用post........");
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HandlerMapping handler=getHandler(req);
        if (handler==null){
            resp.getWriter().write("<font size='25' color='red'>404 Not Found</font><br/><font color='green'><i>Copyright@GupaoEDU</i></font>");
            return;
        }
        HandlerAdapter ha=getHandlerAdapter(handler);

        ModelAndView mv=ha.handle(req,resp,handler);
        
        
        processDispatchResult(resp,mv);
    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) throws  Exception{

        if(null==mv){
            return;
        }
        if(this.viewResolvers.isEmpty()){return ;}

        for (ViewResolver viewResolver : this.viewResolvers) {
            if(!mv.getViewName().equals(viewResolver.getViewName())){continue;}
            String out=viewResolver.viewResolver(mv);
            if (out!=null){
                resp.getWriter().write(out);
                break;
            }
        }
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        if(this.handlerAdapters.isEmpty()){
            return null;
        }
        return this.handlerAdapters.get(handler);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {

        if(this.handlerMappings.isEmpty()){
            return null;
        }
        String url=req.getRequestURI();
        String contextPath=req.getContextPath();
        url = url.replace(contextPath,"").replaceAll("/+","/");

        for (HandlerMapping handler : this.handlerMappings) {
            Matcher matcher=handler.getPattern().matcher(url);
            if (!matcher.matches()){
                continue;
            }
            return handler;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        ApplicationContext context=new ApplicationContext(config.getInitParameter(LOCATION));
        inistrategies(context);
    }

    protected void inistrategies(ApplicationContext context){

        initMultiparResolver(context);
        initLocaleResolver(context);
        initThemmeResolver(context);
        initHandlerMapping(context);
        initHandlerAdapters(context);
        
        initHandlerExceptionResolvers(context);
        
        initRequestToViewNameTranslator(context);
        
        initViewResolvers(context);
        
        initFlashManager(context);

        
    }

    private void initFlashManager(ApplicationContext context) {
    }

    private void initViewResolvers(ApplicationContext context) {
        String templateRoot=context.getConfig().getProperty("templateRoot");
        String templateRootPath=this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir=new File(templateRootPath);
        for (File temple : templateRootDir.listFiles()) {
            this.viewResolvers.add(new ViewResolver(temple.getName(),temple));
        }
    }

    private void initRequestToViewNameTranslator(ApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(ApplicationContext context) {
    }

    private void initHandlerAdapters(ApplicationContext context) {

        for (HandlerMapping handlerMapping : this.handlerMappings) {
            Map<String,Integer> paramMapping=new HashMap<String,Integer>();

            Annotation[][]pa=handlerMapping.getMethod().getParameterAnnotations();

            for (int i=0;i<pa.length;i++){
                for (Annotation a:pa[i]){
                    if (a instanceof RequestParam){
                        String paraName=((RequestParam) a).value();
                        if (!"".equals(paraName.trim())){
                            paramMapping.put(paraName,i);
                        }
                    }
                }
            }
            Class<?> [] paramTypes=handlerMapping.getMethod().getParameterTypes();
            for(int i=0;i<paramTypes.length;i++){
                Class<?> type=paramTypes[i];
                if(type==HttpServletRequest.class||type==HttpServletResponse.class){
                    paramMapping.put(type.getName(),i);
                }
            }
            this.handlerAdapters.put(handlerMapping,new HandlerAdapter(paramMapping));
        }

    }

    private void initThemmeResolver(ApplicationContext context) {
    }

    private void initLocaleResolver(ApplicationContext context) {
        
    }

    private void initMultiparResolver(ApplicationContext context) {
    }

    private void initHandlerMapping(ApplicationContext context) {

        String[] beanNames=context.getBeanDefinitonNames();

        try {
            for (String beanName : beanNames) {
                Object proxy = context.getBean(beanName);
                Object Controller = AopProxyUtils.getTargetObject(proxy);
                Class<?> clazz = Controller.getClass();
                if (!clazz.isAnnotationPresent(com.spring.formework.annotation.Controller.class)) {
                    continue;
                }
                String baseUrl = "";
                if (clazz.isAnnotationPresent(RequestMapper.class)) {
                    RequestMapper requestMapper = clazz.getAnnotation(RequestMapper.class);
                    baseUrl = requestMapper.value();
                }

                Method[] methods = clazz.getMethods();

                for (Method method : methods) {
                    if (!method.isAnnotationPresent(RequestMapper.class)) {
                        continue;
                    }
                    RequestMapper requestMapper = method.getAnnotation(RequestMapper.class);
                    String regex = ("/" + baseUrl + requestMapper.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMappings.add(new HandlerMapping(Controller, method, pattern));
                    System.out.println("Mapping" + regex + "," + method);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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




    private String lowerFirstCase(String str){
        char[] chars=str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

}
