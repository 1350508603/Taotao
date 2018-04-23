package com.taotao.context.service.impl;

import com.alibaba.fastjson.JSON;
import com.taoato.util.JsonUtils;
import com.taotao.context.service.ContextService;
import com.taotao.jedis.JediClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
