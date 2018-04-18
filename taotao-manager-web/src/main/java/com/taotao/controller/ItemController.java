package com.taotao.controller;

import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by john on 2018-04-18.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    public  String findItem(){

        return "index";
    }

}
