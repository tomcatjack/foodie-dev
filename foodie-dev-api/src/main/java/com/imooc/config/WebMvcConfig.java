package com.imooc.config;

import com.imooc.controller.UserTockenIntercepter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  /**
   * 实现静态资源的映射
   *
   * @author luqi
   * @date 2020/4/5
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        // 映射swagger2
        .addResourceLocations("classpath:/META-INF/resources/")
        // 映射本地静态资源
        .addResourceLocations("file:E:\\workspace\\foodie-dev\\image\\200402G53GMS1FCH");
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  /**
   * 声明userTockenIntercepter拦截器
   *
   * @author luqi
   * @date 2020/4/29
   */
  @Bean
  public UserTockenIntercepter userTockenIntercepter() {
    return new UserTockenIntercepter();
  }

  /**
   * 注解拦截器
   *
   * @author luqi
   * @date 2020/4/29
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(userTockenIntercepter()).addPathPatterns("/hello");
    WebMvcConfigurer.super.addInterceptors(registry);
  }
}
