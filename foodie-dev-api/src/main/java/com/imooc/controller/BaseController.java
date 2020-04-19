package com.imooc.controller;

import com.imooc.pojo.Orders;
import com.imooc.service.center.MyOrdersService;
import com.imooc.utils.IMOOCJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

  public static final String FOODIE_SHOPCART = "shopcart";

  /**
   * 默认页大小
   */
  public static final Integer COMMON_PAGE_SIZE = 10;

  public static final Integer PAGE_SIZE = 20;

  /**
   * 支付中心的调用地址
   */

  String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

//  String paymentUrl = "http://localhost:8088/payment/createMerchantOrder";

  /**
   * 微信支付成功 -> 支付中心 -> 天天吃货平台
   * 回调通知的url
   */

  String payReturnUrl = "http://api.z.mukewang.com/foodie-dev-api/orders/notifyMerchantOrderPaid";

//  String payReturnUrl = "http://localhost:8088/orders/notifyMerchantOrderPaid";

  /**
   * 用户上传头像的位置
   */

  public static final String IMAGE_USER_FACE_LOCATION = "E:/workspace/foodie-dev/image";

//  @Autowired
//  public MyOrdersService myOrdersService;

//  /**
//   * 用于验证用户和订单是否有关联关系，避免非法用户调用
//   */
//  public IMOOCJSONResult checkUserOrder(String userId, String orderId) {
//    Orders order = myOrdersService.queryMyOrder(userId, orderId);
//    if (order == null) {
//      return IMOOCJSONResult.errorMsg("订单不存在！");
//    }
//    return IMOOCJSONResult.ok(order);
//  }
}
