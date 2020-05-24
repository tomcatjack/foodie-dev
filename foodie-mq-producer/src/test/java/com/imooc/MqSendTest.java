package com.imooc;

import com.imooc.component.RabbitMqSender;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqSendTest {

  @Autowired
  private RabbitMqSender rabbitMqSender;

  @Test
  public void sendInfo() {
    Map<String, Object> properties = new HashMap<>();
    properties.put("name", "luqi");
    rabbitMqSender.send("这是一条消息", properties);
  }

}
