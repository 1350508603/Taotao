package com.taotao.service.impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by john on 2018-04-17.
 */

@Service
public class ItemServiceimpl implements ItemService {
    @Autowired
    private TbItemMapper emService;

    public TbItem findItem(Long id){

        return   emService.selectByPrimaryKey(id);
    }
}
