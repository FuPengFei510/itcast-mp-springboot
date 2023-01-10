package com.fpf.mp;

import com.fpf.mp.mapper.UserMapper;
import com.fpf.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestMybatisSpringBoot {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testQueryAll(){
        List<User> users = this.userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
