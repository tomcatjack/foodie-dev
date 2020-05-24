package com.imooc.component;

import java.util.Map;
import java.util.UUID;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
/** 
 * 消息发送
 *
 * @author luqi
 * @date 2020/5/24
 */ 
@Component
public class RabbitMqSender {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  /** 
   * 这里是确认消息回调 监听接口  用于确认消息是否被broker所接收
   *
   * @author luqi
   * @date 2020/5/21
   */ 
  final ConfirmCallback confirmCallback = new ConfirmCallback() {

    /**
     * @date 2020/5/21 22:33
     * @param correlationData 作为消息的唯一标识, b 是否落盘成功, s 返回信息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
      System.out.println("确认消息回调correlationData："+correlationData.getId());
      System.out.println("确认消息回调boolean："+b);
      System.out.println("确认消息回调msg："+s);
    }
  };

  /**
   * 对外发送消息
   * 
   * @author luqi
   * @date 2020/5/21 22:36
   * @param message 具体消息, properties 额外参数
   */
  public void send(Object message, Map<String,Object> properties){

    //构建消息 包含消息体和额外参数
    MessageHeaders messageHeaders = new MessageHeaders(properties);
    Message<?> msg = MessageBuilder.createMessage(message, messageHeaders);
    rabbitTemplate.setConfirmCallback(confirmCallback);
    //构建作用 ？？TODO
    MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
      @Override
      public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message)
          throws AmqpException {
        System.out.println("postProcessMessage:"+message);
        return message;
      }
    };

    //构建消息唯一辨别标识
    CorrelationData correlationData = new CorrelationData("luqi_"+UUID.randomUUID().toString());
    rabbitTemplate.convertAndSend("exchange-1","springboot.luqi",msg,messagePostProcessor,correlationData);
  }

}
