package com.imooc.redislocak;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/** 
 * 使用 StringRedisTemplate 集成 lua
 *
 * @author luqi
 * @date 2020/5/28
 */ 
@RestController
@RequestMapping("redis")
public class RedisLockLuaController {

  private static final Logger logger = LoggerFactory.getLogger(RedisLockLuaController.class);

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  private static final String publicKey = "DEMO_COMMON_KEY";

  Boolean lock = false;

  private DefaultRedisScript<String> lockScript;

  @GetMapping("/setlocklua")
  private Object set() {
    String value = UUID.randomUUID().toString();
    lock = luaExpress(publicKey, "30", value);
    if (lock) {
      logger.info("start lock lockNxExJob success");
    } else {
      String a = (String) stringRedisTemplate.opsForValue().get(publicKey);
      logger.warn("key have exist belong to:" + a);
    }
    return "set ok";
  }

  /**
   * 获取lua结果
   */
  public Boolean luaExpress(String key, String time, String value) {
    lockScript = new DefaultRedisScript<String>();
    //获取lua脚本
    lockScript.setScriptSource(
        new ResourceScriptSource(new ClassPathResource("redislock.lua")));
    lockScript.setResultType(String.class);
    // 封装参数
    List<String> keyList = new ArrayList<String>();
    keyList.add(key);
    keyList.add(time);
    keyList.add(value);
    String result = (String) stringRedisTemplate.execute(lockScript, keyList);
    System.out.println(result);
    logger.info("redis set result：" + result);
    if (!"ok".equals(result.toLowerCase())) {
      return false;
    }
    return true;
  }
}
