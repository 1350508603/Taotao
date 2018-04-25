package com.taotao.context.service.impl;


import com.alibaba.fastjson.JSON;
import com.taoato.util.JsonUtils;


import com.taotao.context.service.ContextService;
import com.taotao.jedis.JediClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import com.taotao.pojo.EasyUIDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * Created by john on 2018-04-19.
 */
@Service
public class ContextServiceimpl  implements ContextService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JediClient jediClient;

     public   List<TbContent> getContentByCid(long cid){
         //先查询缓存
         //不影响正常的业务逻辑
         try {
             String josn=jediClient.hget("INDEX-CONTEXT",cid+"");

            if(StringUtils.isNoneBlank(josn)){

                List<TbContent> list= JsonUtils.jsonToList(josn,TbContent.class);

                return  list;

            }



         }catch (Exception e){
             e.printStackTrace();
         }

         TbContentExample example = new TbContentExample();

         TbContentExample.Criteria criteria = example.createCriteria();

         criteria.andCategoryIdEqualTo(cid);

         List<TbContent> list=tbContentMapper.selectByExample(example);
         //把结果添加到缓存
         try {

             jediClient.hset("INDEX-CONTEXT",cid+"", JSON.toJSONString(list));
         }catch (Exception e){
             e.printStackTrace();
         }


         return  list;
    }



   public EasyUIDataGridResult getContentList(long categoryId,int page,int rows){

       //设置分页信息
       PageHelper.startPage(page, rows);
       //执行查询
       TbContentExample example = new TbContentExample();
       TbContentExample.Criteria createCriteria = example.createCriteria();
       createCriteria.andCategoryIdEqualTo(categoryId);
       //获取查询结果
       List<TbContent> list = tbContentMapper.selectByExample(example);
       PageInfo<TbContent> pageInfo = new PageInfo<>(list);
       EasyUIDataGridResult result = new EasyUIDataGridResult();
       result.setRows(list);
       result.setTotal(pageInfo.getTotal());
       //返回结果
       return result;
   }

    public TaotaoResult addContent(TbContent content) {
        //补充属性
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //添加
        tbContentMapper.insert(content);
        //返回结果
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContent(TbContent content) {
        // 填充属性
        content.setUpdated(new Date());
        //更新内容
        tbContentMapper.updateByPrimaryKey(content);
        //返回结果
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContent(String ids) {
        String[] idList = ids.split(",");
        for(String id : idList){
            //删除内容
            tbContentMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
        //返回结果
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult getContent(long id) {
        TbContent content = tbContentMapper.selectByPrimaryKey(id);
        return TaotaoResult.ok(content);
    }


}
