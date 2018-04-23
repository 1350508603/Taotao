package com.taotao.controllers;

import com.taotao.intreface.TbItemService;
import com.taotao.pojo.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2018/4/23.
 */
@Controller
public class ItemControllers {

    @Autowired
    private TbItemService  tbItemService;

   @RequestMapping("/")
    public String list(){

        return  "index";
    }
    @RequestMapping("/{page}")
    public String page(@PathVariable String page){

        return page;
    }


    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult llal(Integer page, Integer rows){

        return tbItemService.gteItemList(page,rows);
    }
}
