package com.taotao.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by john on 2018-04-20.
 */

public class jeds {
    public static void main(String[] args){
        //单机版通过jedis指定主机名和端口号
       /* Jedis jedis=new Jedis("47.106.154.159",6379);
        jedis.set("xiao","1234");
         String dj=jedis.get("xiao");
        System.out.print(dj);
        jedis.close();*/

    //连接池操作NOSQL
 JedisPool   je=new JedisPool("47.106.154.159",6379);
        //获取连接
        Jedis  j=je.getResource();
        //使用jds操作redis
       j.set("kl","tyu");
        String bu=j.get("kl");
        System.out.print(bu);
        j.close();
        je.close();

    }
    //通过JedisCluster对象连接集群操作数据库
    public static   void  jediscutl(){
        //通过jediscluster构造参数set类型集合中的每个元素hostandport
        Set<HostAndPort> node =new HashSet<HostAndPort>();
        //直接使用jedisculter操作redis，自带连接池，对象可以是单例。
        node.add(new HostAndPort("47.106.154.159",8999));
         node.add(new HostAndPort("47.106.154.159",46666));
        JedisCluster  jedisCluster=new JedisCluster(node);
        jedisCluster.set("sss","djfk");
        jedisCluster.close();



    }
}
