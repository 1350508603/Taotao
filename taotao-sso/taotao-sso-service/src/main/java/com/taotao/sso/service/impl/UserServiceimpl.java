package com.taotao.sso.service.impl;

import com.taoato.util.JsonUtils;
import com.taotao.jedis.JediClient;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by john on 2018-04-24.
 */
@Service
public class UserServiceimpl implements UserService {

    @Value("${USER_SESSION}")
    private  String USER_SESSION;
    @Value("${SESSION_EXPIR}")
    private Integer SESSION_EXPIR;
    @Autowired
    private JediClient jediClient;
     @Autowired
     private  TbUserMapper  tbUserMapper;
     @Override
     public TaotaoResult charkdata(String date, int type) {
        TbUserExample  example=new TbUserExample();
        //设置查询条件
         TbUserExample.Criteria create=example.createCriteria();
         //判断用户名是否可用
         if (type == 1){
             create.andUsernameEqualTo(date);
         }else  if (type == 2){
             create.andPhoneEqualTo(date);
         }else  if (type == 3){
             create.andEmailEqualTo(date);
         }else {
             return  TaotaoResult.build(400,"非法信息");
         }

         List<TbUser> list= tbUserMapper.selectByExample(example);
         if (list.size()>0 && list !=null ){
             return  TaotaoResult.ok(false);
         }
          return TaotaoResult.ok(true);
    }

      public TaotaoResult register (TbUser user){
         //检查数据的有效性
          if(StringUtils.isBlank(user.getUsername())){
              return  TaotaoResult.build(400,"用户名不能为空");
          }
               TaotaoResult result=charkdata(user.getUsername(),1);
              if (!(boolean)result.getData()){
                  TaotaoResult.build(400,"用户名不能重复");
              }
              if (StringUtils.isBlank(user.getPhone())){
                  return  TaotaoResult.build(400,"手机不能为空");
              }
              TaotaoResult result1=  charkdata(user.getPhone(),2);
              if (!(boolean)result1.getData()){
                  return  TaotaoResult.build(400,"手机不能重复");
              }
              if(StringUtils.isBlank(user.getPassword())){

                  return  TaotaoResult.build(400,"密码不能为空");
              }
/*
             if (StringUtils.isBlank(user.getEmail())){
                  return  TaotaoResult.build(400,"密码不能为空");
             }
              TaotaoResult result2=     charkdata(user.getEmail(),3);
              if (!(boolean)result2.getData()){
                 return  TaotaoResult.build(400,"emil不能重复");
               }*/
             user.setCreated(new Date());
             user.setUpdated(new Date());

             String  adi5=    DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(adi5);

             tbUserMapper.insert(user);

            return  TaotaoResult.ok();
    }

         public   TaotaoResult login(String username,String password){
           TbUserExample example=new TbUserExample();
           TbUserExample.Criteria crice = example.createCriteria();
           crice.andUsernameEqualTo(username);
           List<TbUser>  list=tbUserMapper.selectByExample(example);
           if (list.size()==0 || list==null ){
               return  TaotaoResult.build(400,"用户名或密码错误");
           }
           TbUser user=list.get(0);
           System.out.print(user.getPassword());
            if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword()
            )){
                return  TaotaoResult.build(400,"密码不正确");
            }
            //生成tock
             String tock= UUID.randomUUID().toString();
            System.out.print(tock);
            //保存到redis
             user.setPassword(null);
             jediClient.set(USER_SESSION+":"+tock, JsonUtils.objectToJson(user));
             jediClient.expire(USER_SESSION+":"+tock,SESSION_EXPIR);
             return  TaotaoResult.ok(tock);
    }
      public   TaotaoResult getUserbyTock(String tock){
             String jeds=jediClient.get(USER_SESSION+":"+tock);
             if (StringUtils.isBlank(jeds)){
                 TaotaoResult.build(400,"登录超时，请重新登录");
             }

             TbUser user=JsonUtils.jsonToPojo(jeds,TbUser.class);
             System.out.print(user);
             return TaotaoResult.ok(user);
      }


}
