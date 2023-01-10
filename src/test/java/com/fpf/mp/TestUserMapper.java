package com.fpf.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fpf.mp.mapper.UserMapper;
import com.fpf.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {
    @Autowired
    private UserMapper userMapper;

    /**
     * 插入数据的操作
     */
    @Test
    public void insertUser(){
        User user = new User();
        user.setUserName("jack");
        user.setPassword("1234");
        user.setName("杰克");
        user.setAge(21);
        user.setMail("jack168@qq.com");
        user.setAddress("江西赣州");
        int affect = userMapper.insert(user);
        //返回受影响的行数：
        System.out.println(affect);
        //获取自增长的id值：   需要在User实体类中进行设置增长的生成策略
        System.out.println("自增长的id---->"+user.getId());
    }


    @Test     //测试根据用户的id进行更新
    public void updateUserById(){
        User user = new User();
        user.setId(1L);              //设置查询的条件
        user.setPassword("1314");   //更新的字段
        user.setAge(22);            //更新的字段
        int result = userMapper.updateById(user);
        System.out.println("受影响的行数："+result);
    }

    /**
     * 测试根据条件进行更新：
     *     QueryWrapper
     */
    @Test
    public void updateUser1(){
        //设置更新的字段：
        User user = new User();
        user.setAge(22);
        user.setPassword("1987");
        //设置修改的条件：
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","zhaoliu");

        int update = userMapper.update(user, wrapper);
        System.out.println(update);
    }

    /**
     * 另一种通过条件进行更新的方法：
     *      UpdateWrapper()
     */
    @Test
    public void updateUser2(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("name","小明").set("password","110120")    //设置更新的字段
        .eq("id",6);                 //设置修改的条件
        int update = userMapper.update(null, wrapper);
        System.out.println("result----->"+update);
    }

    /**
     * 通过用户的id进行删除
     */
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(6L);
        System.out.println("result----->"+result);
    }

    /**
     * 通过map集合进行删除数据
     */
    @Test
    public void testDeleteByMap(){
        Map<String,Object> map = new HashMap<>();
        //设置删除的条件：  有多个条件时，是and的关系
        map.put("user_name","ls");
        map.put("password","1234");
        int result = userMapper.deleteByMap(map);
        System.out.println("result----->"+result);
    }

    /**
     * 通过wrapper类进行删除数据：
     */
    @Test
    public void testDeleteByWrapper(){
        //方式一：
        //QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.eq("user_name","jack").eq("password","1234");

        //方式二：  常用
        User user = new User();
        user.setUserName("ls");
        user.setPassword("1234");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int result = userMapper.delete(wrapper);
        System.out.println("result----->"+result);
    }

    /**
     * 根据用户的id进行批量的删除
     */
    @Test
    public void deleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(10L, 11L));
        System.out.println("result----->"+result);
    }

    /**
     * 通过用户的id进行查询
     */
    @Test
    public void selectUser(){
        User user = userMapper.selectById(1);
        //测试@TableField:    查询时不返回该属性的值
        System.out.println(user);
    }

    /**
     * 根据id进批量查询
     */
    @Test
    public void selectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L, 4L, 100L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 根据wrapper查询一条数据
     * 查询结果有多条时，会抛出异常
     */
    @Test
    public void selectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);

    }

    /**
     * 根据条件查询总记录数
     */
    @Test
    public void selectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询年龄大于22岁的总记录数
        wrapper.gt("age",22);
        Integer integer = userMapper.selectCount(wrapper);
        System.out.println(integer);
    }

    /**
     * 根据wrapper条件返回一个list集合
     */
    @Test
    public void selectList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询的条件
        wrapper.like("email","itcast");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 进行分页查询：
     *  1. 创建MyBatisConfig类进行配置分页插件
     *  2. 进行测试
     */
    @Test
    public void selectPage(){
        //查询第一页，查询2条数据
        Page<User> page = new Page<>(1,2);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询的条件
        wrapper.like("email","itcast");

        IPage<User> iPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据的总条数："+iPage.getTotal());
        System.out.println("数据的总页数："+iPage.getPages());
        System.out.println("当前页数："+iPage.getCurrent());

        //获取所有的数据：
        List<User> records = iPage.getRecords();
        for (User user : records) {
            System.out.println(user);
        }
    }
}
