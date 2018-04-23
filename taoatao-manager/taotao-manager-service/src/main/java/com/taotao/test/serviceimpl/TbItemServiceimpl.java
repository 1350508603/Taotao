package com.taotao.test.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.intreface.TbItemService;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2018/4/23.
 */

@Service
public class TbItemServiceimpl implements TbItemService{
    @Autowired
    private TbItemMapper tbItemMapper;

    public EasyUIDataGridResult gteItemList(int page , int rows){
        PageHelper.startPage(page,rows);

        TbItemExample example=new TbItemExample();
        List<TbItem> list=tbItemMapper.selectByExample(example);

        PageInfo<TbItem> pageInfo=new PageInfo<>(list);


        EasyUIDataGridResult result =new EasyUIDataGridResult();

        result.setRows(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

}
