package com.imooc.redislocak;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedissonController {

  private static final Logger logger = LoggerFactory.getLogger(RedisLockController.class);

  @Autowired
  private RedissonClient redissonClient;

  @GetMapping("/setRedissonlock")
  public void testRedis(){
    RLock order = redissonClient.getLock("order");
    logger.info("我进入了锁");
    order.lock(3, TimeUnit.SECONDS);
    logger.info("获得了锁");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
      logger.info("释放了锁");
      order.unlock();
    }
    logger.info("方法执行完毕");
  }

}
