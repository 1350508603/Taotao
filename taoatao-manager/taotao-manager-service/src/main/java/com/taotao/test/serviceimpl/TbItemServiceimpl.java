package com.taotao.test.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoato.util.IDUtils;
import com.taoato.util.TaotaoResult;
import com.taotao.intreface.TbItemService;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2018/4/23.
 */

@Service
public class TbItemServiceimpl implements TbItemService{
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper  tbItemDescMapper;

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

    public TaotaoResult addItem(TbItem item, String decs){
        //生成一个以毫秒加2位数的id
       long id= IDUtils.genItemId();
        //添加id
       item.setId(id);
       //商品状态
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //新增
        tbItemMapper.insert(item);

        TbItemDesc tbItemDesc=new TbItemDesc();

        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(decs);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());


        tbItemDescMapper.insert(tbItemDesc);

            return TaotaoResult.ok();

    };

}
