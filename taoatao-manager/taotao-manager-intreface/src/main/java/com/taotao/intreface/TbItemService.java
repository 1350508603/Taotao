package com.taotao.intreface;

import com.taoato.util.TaotaoResult;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;

/**
 * Created by admin on 2018/4/23.
 */
public interface TbItemService {


    EasyUIDataGridResult gteItemList(int page , int rows);

    TaotaoResult addItem(TbItem item,String decs);
 /*   int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);*/

    /*List<TbItem> selectByExample(TbItemExample example);*/

   /* TbItem selectByPrimaryKey(Long id);*/

  /*  int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);*/
}
