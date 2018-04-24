package com.taotao.sso.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by john on 2018-04-23.
 */
@Controller
public class Controllers {

    @RequestMapping("/register")
    public  String register(){

        return "register";
    }

    @RequestMapping("/login")
    public String login(){

        return "login";
    }
}
