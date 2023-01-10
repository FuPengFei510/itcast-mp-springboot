package com.fpf.mp;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration       //设置为配置类
@MapperScan("com.fpf.mp.mapper") //设置mapper接口的扫描包
public class MyBatisPlusConfig {

    /**
     * PaginationInterceptor:     拦截器
     * @return
     */
    @Bean       //配置分页插件
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
