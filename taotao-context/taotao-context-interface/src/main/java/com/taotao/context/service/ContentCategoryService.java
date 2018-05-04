package com.taotao.context.service;

import java.util.List;
import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;
/**
 * Created by admin on 2018/4/25.
 */
public interface ContentCategoryService {

        List<EasyUITreeNode>  getContentCategoryList(long parentId);
        //添加内容分类，注意参数名称要与content-category.jsp页面指定的参数名称一致
        TaotaoResult addContentCategory(long parentId,String name);
        //修改内容分类，注意参数名称要与content-category.jsp页面指定的参数名称一致
        TaotaoResult updateContentCategory(long id,String name);
        //删除内容分类，注意参数名称要与content-category.jsp页面指定的参数名称一致
        TaotaoResult deleteContentCategory(long id);
}
