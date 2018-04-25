package com.taotao.context.service;


import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.EasyUIDataGridResult;
import java.util.List;

/**
 * Created by john on 2018-04-19.
 */
public interface ContextService {

    List<TbContent> getContentByCid(long cid);

    EasyUIDataGridResult getContentList(long categoryId,int page,int rows);

    //添加内容
    TaotaoResult addContent(TbContent content);
    //修改内容
    TaotaoResult updateContent(TbContent content);
    //删除内容
    TaotaoResult deleteContent(String ids);
    //获取单个内容信息
    TaotaoResult getContent(long id);

}
