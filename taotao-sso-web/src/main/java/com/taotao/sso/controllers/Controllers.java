package com.taotao.sso.controllers;

import com.alibaba.fastjson.JSON;
import com.taoato.util.CookieUtils;
import com.taoato.util.JsonUtils;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;

/**
 * Created by john on 2018-04-23.
 */
@Controller
public class Controllers {

     @Autowired
     private UserService userService;

     @RequestMapping("/user/check/{data}/{type}")
     @ResponseBody
     public TaotaoResult  chackUserdate(@PathVariable String data,@PathVariable int type){

         TaotaoResult result=userService.charkdata(data,type);

         return result;
     }

     @RequestMapping(value = "/user/register",method = RequestMethod.POST)
     @ResponseBody
     public  TaotaoResult register(TbUser user){
         TaotaoResult result= userService.register(user);
         return  result ;
     }
      @RequestMapping(value = "/user/login",method = RequestMethod.POST)
      @ResponseBody
      public  TaotaoResult login(String username, String password, HttpServletResponse response, HttpServletRequest request){

         TaotaoResult result= userService.login(username,password);
         if(result.getStatus()==200){
             //把tock存入Cookie
             CookieUtils.setCookie(request,response,"TT-tock",result.getData().toString());
             System.out.print(result.getData().toString());
         }

          return  result ;
      }

    @RequestMapping(value = "/user/tock/{tock}",method = RequestMethod.GET)
    @ResponseBody
     public  String getUsertock(@PathVariable String tock,String callback){
          TaotaoResult result= userService.getUserbyTock(tock);
           if (StringUtils.isNotBlank(callback)) {
               return callback+"("+ JSON.toJSONString(result)+");";
           }
        return JSON.toJSONString(result);
    }
    @RequestMapping("/register")
    public  String register(){
        return "register";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
