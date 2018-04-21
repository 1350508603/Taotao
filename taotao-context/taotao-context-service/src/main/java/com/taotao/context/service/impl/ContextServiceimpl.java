package com.taotao.context.service.impl;

import com.taotao.context.service.ContextService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
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
     public   List<TbContent> getContentByCid(long cid){
         TbContentExample example = new TbContentExample();
         TbContentExample.Criteria criteria = example.createCriteria();
         criteria.andCategoryIdEqualTo(cid);
         List<TbContent> list=tbContentMapper.selectByExample(example);
         return  list;
    }
}
