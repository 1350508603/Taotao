package com.taotao.context.service.impl;

import com.taotao.context.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taotao.pojo.EasyUITreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/25.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;


    public List<EasyUITreeNode> getContentCategoryList(long parentId) {
        //创建一个查询类
        TbContentCategoryExample contentCategoryExample = new TbContentCategoryExample();
        //设置查询条件
        TbContentCategoryExample.Criteria criteria = contentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //查询
        List<TbContentCategory> categoryList = contentCategoryMapper.selectByExample(contentCategoryExample);
        //将categoryList转换为List<EasyUITreeNode>
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for(TbContentCategory contentCategory : categoryList){
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(contentCategory.getId());
            easyUITreeNode.setText(contentCategory.getName());
            easyUITreeNode.setState(contentCategory.getIsParent() ? "closed":"open");
            resultList.add(easyUITreeNode);
        }
        return resultList;
    }

}
