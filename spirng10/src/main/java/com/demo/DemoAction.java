package com.demo;

import com.service.IDemoService;
import com.spring.annotation.AutoWried;
import com.spring.annotation.Controller;
import com.spring.annotation.RequestMapper;
import com.spring.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qgy
 * @Date: 2018/5/24 11:18
 * @Description:
 */
@Controller
@RequestMapper("/demo")
public class DemoAction {
    @AutoWried private IDemoService demoService;
    @RequestMapper("/query.json")
    public  void query(HttpServletRequest req, HttpServletResponse resp, @RequestParam("name") String name){
        String result=demoService.get(name);
        try {
            resp.getWriter().print(result);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
