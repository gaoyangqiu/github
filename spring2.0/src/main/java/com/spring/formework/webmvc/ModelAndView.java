package com.spring.formework.webmvc;

import java.util.Map;

/**
 * @Author: qgy
 * @Date: 2018/6/4 16:39
 * @Description:
 */
public class ModelAndView {
    private String viewName;
    private Map<String,?> model;

    public ModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
