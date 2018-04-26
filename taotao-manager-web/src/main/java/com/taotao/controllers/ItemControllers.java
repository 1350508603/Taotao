package com.taotao.controllers;


import com.taotao.intreface.TbItemService;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult taotaoResult(TbItem item, String desc){
            TaotaoResult taotaoResult=tbItemService.addItem(item,desc);
        return taotaoResult;
    }


    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem item){
        TaotaoResult result = tbItemService.updateItem(item);
        return result;
    }

    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItem(String ids){
        TaotaoResult result = tbItemService.deleteItem(ids);
        return result;
    }
    //获取单个内容信息
    @RequestMapping("/rest/item/query/")
    @ResponseBody
    public TaotaoResult getItem(Long id){
        TaotaoResult result = tbItemService.getItem(id);
        return result;
    }



}
