package com.spring.demo.action;

import com.spring.demo.service.IModifyService;
import com.spring.demo.service.IQueryService;
import com.spring.formework.annotation.AutoWried;
import com.spring.formework.annotation.Controller;
import com.spring.formework.annotation.RequestMapper;
import com.spring.formework.annotation.RequestParam;
import com.spring.formework.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qgy
 * @Date: 2018/6/15 11:18
 * @Description:
 */
@Controller
@RequestMapper("/web")
public class MyAction {


    @AutoWried
    IQueryService queryService;

    @AutoWried
    IModifyService modifyService;

    @RequestMapper("/query.json")
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("name") String name
    ){
        String result = queryService.query(name);
        System.out.println(result);
        return out(response,result);

    }

    @RequestMapper("/add*.json")
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response,
                              @RequestParam("name") String name,@RequestParam("addr") String addr){
        String result = modifyService.add(name,addr);
        return out(response,result);
    }

    @RequestMapper("/remove.json")
    public ModelAndView remove(HttpServletRequest request,HttpServletResponse response,
                                 @RequestParam("id") Integer id){
        String result = modifyService.remove(id);
        return out(response,result);
    }

    @RequestMapper("/edit.json")
    public ModelAndView edit(HttpServletRequest request,HttpServletResponse response,
                               @RequestParam("id") Integer id,
                               @RequestParam("name") String name){
        String result = modifyService.edit(id,name);
        return out(response,result);
    }



    private ModelAndView out(HttpServletResponse resp,String str){
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
