package com.imooc.controller;

import com.imooc.service.CarouselService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 *
 * @author luqi
 * @date 2020/3/29
 */
@RestController
public class HelloController {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

  @Autowired
  CarouselService carouselService;

  @GetMapping("/hello")
  private Object get(){
    LOGGER.debug("debug");
    LOGGER.info("info");
    LOGGER.warn("warn");
    LOGGER.error("error");
    return "hello controller";
  }

  @GetMapping("/get")
  private Object getCarousel(String id){
    return carouselService.getByKey(id);
  }

}
