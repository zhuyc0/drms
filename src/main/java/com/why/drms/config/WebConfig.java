package com.why.drms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月25日 18:33
 * @desc WebConfig web 配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login.html").setViewName("login");
        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("userinfo.html").setViewName("userinfo");
        registry.addViewController("changepass.html").setViewName("changepass");
        registry.addViewController("main.html").setViewName("main");
        registry.addViewController("campus/index.html").setViewName("campus/index");
        registry.addViewController("floor/index.html").setViewName("floor/index");
        registry.addViewController("room/index.html").setViewName("room/index");
        registry.addViewController("user/index.html").setViewName("user/index");
        registry.addViewController("my_manager/index.html").setViewName("my_manager/index");
        registry.addViewController("user/add.html").setViewName("user/add");
        registry.addViewController("room/add.html").setViewName("room/add");
        registry.addViewController("floor/add.html").setViewName("floor/add");
        registry.addViewController("my_manager/student.html").setViewName("my_manager/student");
        registry.addViewController("my_manager/wjadd.html").setViewName("my_manager/wjadd");
        registry.addViewController("my_manager/wjshow.html").setViewName("my_manager/wjlist");
        registry.addViewController("my_manager/wxadd.html").setViewName("my_manager/wxadd");
        registry.addViewController("my_manager/wxshow.html").setViewName("my_manager/wxlist");
    }
}
