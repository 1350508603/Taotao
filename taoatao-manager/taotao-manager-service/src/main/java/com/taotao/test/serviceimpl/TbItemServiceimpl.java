package com.taotao.test.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taoato.util.IDUtils;
import com.taotao.pojo.*;
import com.taotao.intreface.TbItemService;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
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

    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name="itemAddTopic")
    private Destination destination;


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
       final long id= IDUtils.genItemId();
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

        //发送activemq消息
        jmsTemplate.send(destination,new MessageCreator() {


            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(id+"");
                return textMessage;
            }
        });

        return TaotaoResult.ok();

    }

        /*
        修改
            */
    public TaotaoResult updateItem(TbItem tbItem) {
        // 填充属性
        tbItem.setUpdated(new Date());
        //更新内容
        tbItemMapper.updateByPrimaryKey(tbItem);
        //返回结果
        return TaotaoResult.ok();
    }


    public TaotaoResult deleteItem(String ids) {
        String[] idList = ids.split(",");
        for(String id : idList){
            //删除内容
            tbItemMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
        //返回结果
        return TaotaoResult.ok();
    }


    public TaotaoResult getItem(long id) {
        TbItem item = tbItemMapper.selectByPrimaryKey(id);
        return TaotaoResult.ok(item);
    }




}
