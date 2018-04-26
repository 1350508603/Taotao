package com.taotao.intreface;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;

/**
 * Created by admin on 2018/4/23.
 */
public interface TbItemService {


    EasyUIDataGridResult gteItemList(int page , int rows);

    TaotaoResult addItem(TbItem item,String decs);

    //修改内容
    TaotaoResult updateItem(TbItem content);
    //删除内容
    TaotaoResult deleteItem(String ids);
    //获取单个内容信息
    TaotaoResult getItem(long id);
}
