package com.imooc.redislocak;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisLockController {

  private static final Logger logger = LoggerFactory.getLogger(RedisLockController.class);

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  private static final String publicKey="CommonKey";

  @GetMapping("/setlock")
  private Object set() throws InterruptedException {
    String currTime = UUID.randomUUID().toString();
    Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(publicKey, currTime);
    if(aBoolean){
      stringRedisTemplate.opsForValue().set(publicKey,currTime,60, TimeUnit.SECONDS);
      logger.info("start lock lockNxExJob success");
    }else{
      String s = stringRedisTemplate.opsForValue().get(publicKey);
      logger.error("key have exist belong to:"+s);
      Thread.sleep(5000);
    }
    return "set ok";
  }

}
