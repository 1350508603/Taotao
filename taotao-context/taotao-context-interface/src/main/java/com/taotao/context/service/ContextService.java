package com.taotao.context.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by john on 2018-04-19.
 */
public interface ContextService {

    List<TbContent> getContentByCid(long cid);

}
