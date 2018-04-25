package com.taotao.context.service;

import java.util.List;
import com.taotao.pojo.EasyUITreeNode;
/**
 * Created by admin on 2018/4/25.
 */
public interface ContentCategoryService {

        List<EasyUITreeNode>  getContentCategoryList(long parentId);

}
