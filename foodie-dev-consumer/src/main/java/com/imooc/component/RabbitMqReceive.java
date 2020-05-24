package com.imooc.component;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
/** 
 * 消息的监听
 *
 * @author luqi
 * @date 2020/5/23
 */ 
@Component
public class RabbitMqReceive {

  @RabbitListener(
      bindings = @QueueBinding(
                    value = @Queue(value = "queue-1",durable = "true"),
                    exchange = @Exchange(name = "exchange-1",
                        type = "topic",
                        ignoreDeclarationExceptions = "true"
                    ),
                    key = "springboot.*"
      )
  )
  @RabbitHandler
  public void onMessage(Message message, Channel channel) throws Exception{
    System.out.println("+================================+");
    //获取消息体
    System.out.println("Payload："+message.getPayload());

    //获取deliveryTag 并进行手工ACK操作
    Long aLong = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
    channel.basicAck(aLong,true);
  }

}
