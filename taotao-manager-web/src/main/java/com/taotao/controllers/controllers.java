package com.taotao.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by john on 2018-04-21.
 */
@Controller
public class controllers {
    @RequestMapping("index")
    public  String     index(){

        return  "index";
    }

    @RequestMapping("/{page}")
    public String pge(@PathVariable String page) {

        return page;
    }

}
