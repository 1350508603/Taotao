package com.taotao.controllers;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/index")
    public ModelAndView findItem(ModelAndView modelAndView, HttpSession session){
         List<TbItem> itbm= itemService.findItem();
         session.setAttribute("item",itbm);
         modelAndView.addObject("item",itbm);
         modelAndView.setViewName("index");
         return  modelAndView;
    }

}
