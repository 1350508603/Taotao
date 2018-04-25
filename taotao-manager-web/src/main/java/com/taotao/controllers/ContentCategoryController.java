package com.taotao.controllers;

import com.taotao.context.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.taotao.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by admin on 2018/4/24.
 */
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
        List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);

        return list;
    }
}
