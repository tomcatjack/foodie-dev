package com.imooc.controller;

import com.imooc.cache.RedisUtils;
import com.imooc.pojo.Users;
import com.imooc.pojo.vo.TestVO;
import com.imooc.service.UserService;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 *
 * @author luqi
 * @date 2020/3/29
 */
@RestController
@RequestMapping("redis")
public class RedisController {

  @Autowired
  RedisOperator redisOperator;

  @Autowired
  UserService userService;

  @Autowired
  RedisUtils redisUtils;

  @GetMapping("/setcache/{name}")
  private Object setcache(@PathVariable("name")  String name) {
    Users imooc = userService.getByName(name);
    return JsonUtils.objectToJson(imooc);
  }

  @GetMapping("/set")
  private Object set(String name, String value) {
//    redisOperator.set(name, value);
    redisUtils.set(name, value);
    return "set ok";
  }

  @GetMapping("/setObject")
  private Object setObject() {
    TestVO testVO = new TestVO();
    testVO.setName("name");
    testVO.setId(1L);
    redisOperator.set("TEST", JsonUtils.objectToJson(testVO));
    return "set ok";
  }

  @GetMapping("/get")
  private Object get(String name) {
    String s = redisOperator.get(name);
    return s;
  }

}
