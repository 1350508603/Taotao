package com.taotao.porta.controllers;



import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.taotao.context.service.ContextService;
import com.taotao.pojo.TbContent;
import com.taotao.porta.pojo.AD1Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2018-04-19.
 */

@Controller
public class Controllers {
       @Autowired
       private ContextService contextService;
    @Value("${AD1_CATEGORY_ID}")
    private Long AD1_CATEGORY_ID;
    @Value("${AD1_WIDTH}")
    private Integer AD1_WIDTH;
    @Value("${AD1_WIDTH_B}")
    private Integer AD1_WIDTH_B;
    @Value("${AD1_HEIGHT}")
    private Integer AD1_HEIGHT;
    @Value("${AD1_HEIGHT_B}")
    private Integer AD1_HEIGHT_B;

@RequestMapping("index")
public  ModelAndView   contro(ModelAndView modelAndView, HttpSession session){

    List<TbContent> contentList = contextService.getContentByCid(AD1_CATEGORY_ID);
    List<AD1Node> ad1Nodes = new ArrayList<>();
    for (TbContent tbContent : contentList) {
        AD1Node node = new AD1Node();
        node.setAlt(tbContent.getTitle());
        node.setHeight(AD1_HEIGHT);
        node.setHeightB(AD1_HEIGHT_B);
        node.setWidth(AD1_WIDTH);
        node.setWidthB(AD1_WIDTH_B);
        node.setSrc(tbContent.getPic());
        node.setSrcB(tbContent.getPic2());
        node.setHref(tbContent.getUrl());
        //添加到节点列表
        ad1Nodes.add(node);
    }
    //把列表转换成json数据
      String alllist= JSON.toJSONString(ad1Nodes);

    //把json数据传递给页面


     modelAndView.addObject("ad1",alllist);

    session.setAttribute("ad1",contentList);
    modelAndView.setViewName("index");
    return modelAndView;
}



}
