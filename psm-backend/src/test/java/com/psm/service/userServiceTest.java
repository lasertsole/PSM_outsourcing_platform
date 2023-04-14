package com.psm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *userService的测试类
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = application.class)//目录结构不相同时用classes
@SpringBootTest
public class userServiceTest {
    @Autowired
    private userService userService;

    @Test
    public void testAdd(){
        userService.add();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /*测试redis连接性*/
    @Test
    public void testSet(){
        //存入数据
        redisTemplate.boundValueOps("name").set("zhangsan");
    }
    @Test
    public void testGet(){
        //获取数据
        Object name = redisTemplate.boundValueOps("name").get();
        System.out.println(name);
    }
}