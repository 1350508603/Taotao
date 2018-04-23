package com.taotao.com.taotao.test;

import com.taotao.jedis.JediClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by john on 2018-04-21.
 */
public class Test {
    public static  void main(String[] args){

        ApplicationContext cont=new ClassPathXmlApplicationContext("classpath:spring/ApplicationJedis.xml");
           JediClient  jediClient=cont.getBean(JediClient.class);
           jediClient.set("ss","ss");
           String  yi=  jediClient.get("ss");
           System.out.print(yi);


    }
}
