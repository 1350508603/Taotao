package com.taotao.intreface;

import com.taotao.pojo.EasyUITreeNode;


import java.util.List;

/**
 * Created by admin on 2018/4/23.
 */
public interface TbItemCatService {

         List<EasyUITreeNode> TbItemCatList(long parentid);
}
