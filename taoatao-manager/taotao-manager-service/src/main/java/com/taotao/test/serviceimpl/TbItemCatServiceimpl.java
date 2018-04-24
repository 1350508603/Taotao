package com.taotao.test.serviceimpl;

import com.taotao.intreface.TbItemCatService;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/23.
 */
@Service
public class TbItemCatServiceimpl implements TbItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    public List<EasyUITreeNode> TbItemCatList(long parentid){

        TbItemCatExample catExample=new TbItemCatExample();

        TbItemCatExample.Criteria criteria=catExample.createCriteria();

        criteria.andParentIdEqualTo(parentid);

        List<TbItemCat> list = tbItemCatMapper.selectByExample(catExample);
        List<EasyUITreeNode> easyUITreeNodes =new ArrayList<>();
        for (TbItemCat tbItemCat :list){
            EasyUITreeNode node=new EasyUITreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
           node.setState(tbItemCat.getIsParent()?"closed":"open");
            easyUITreeNodes.add(node);

        }

        return easyUITreeNodes;
    };
}
