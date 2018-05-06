package com.taotao.sso.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * Created by john on 2018-04-24.
 */
public interface UserService {
    /**
     *检验数据是否可用
     * @param data
     * @param type
     * @return
     */
    TaotaoResult charkdata(String data,int type);


    /**
     * 注册
     * @param user
     * @return
     */
    TaotaoResult register (TbUser user);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    TaotaoResult login(String username,String password);

    /**
     * 查询tock中的内容
     * @param tock
     * @return
     */
    TaotaoResult getUserbyTock(String tock);

}
