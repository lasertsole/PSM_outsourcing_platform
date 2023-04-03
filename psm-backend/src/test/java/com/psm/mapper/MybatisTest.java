package com.psm.mapper;

import com.psm.domain.User;
import com.psm.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testFindAll(){
        List<User> list = userMapper.findAll();
        System.out.println(list);
    }
}
