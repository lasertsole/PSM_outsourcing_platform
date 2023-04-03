package com.psm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *loginController的测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class loginControllerTest {
    @Autowired
    private loginController loginController;

    @Test
    public void testVersion(){
        loginController.version();
    }
}