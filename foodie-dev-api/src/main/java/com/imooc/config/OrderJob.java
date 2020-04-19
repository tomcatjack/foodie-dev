//package com.imooc.config;
//
//import com.imooc.utils.DateUtil;
//import java.util.Date;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * 基于spring 的定时任务  需要再启动类中加上 @EnableScheduling
// *
// * @author luqi
// * @date 2020/4/6
// */
//@Component
//public class OrderJob {
//
//  private static final Logger LOGGER = LoggerFactory.getLogger(OrderJob.class);
//
//  @Scheduled(cron = "0/3 * * * * ?")
//  public void autoCloseOrder(){
//    LOGGER.info("Sringboot的定时任务开始。。。。time:{}", DateUtil.dateToStringWithTime(new Date()));
//  }
//
//}
