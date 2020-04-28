package com.cody.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MyAdapter
 *
 * @author WQL
 * @Description: 配置首页
 * @date: 2020/4/28 23:30
 * @since JDK 1.8
 */
@Configuration
public class MyAdapter implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login.shtml");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
