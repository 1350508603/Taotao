package com.taotao.controllers;

import com.taotao.intreface.TbItemCatService;
import com.taotao.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2018/4/23.
 */

@Controller
public class TbItemCatController {
    @Autowired
    private TbItemCatService tbItemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getcatList(@RequestParam(value="id",defaultValue ="0" )long parentid ){

        List<EasyUITreeNode> list=tbItemCatService.TbItemCatList(parentid);

        return list;


    }
}
