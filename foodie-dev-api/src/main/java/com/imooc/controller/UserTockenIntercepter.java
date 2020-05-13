package com.imooc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义用户登录拦截器
 *
 * @author luqi
 * @date 2020/4/29
 */
public class UserTockenIntercepter implements HandlerInterceptor {

  /**
   * 拦截请求 在controller 调用之前
   *
   * @author luqi
   * @date 2020/4/29
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    System.out.println(">>>>>>preHandle");
    /**
     *  false:表示请求被拦截，被驳回，验证出了问题
     *  true:表示请求通过 验证ok
     */
    return false;
  }

  /**
   * 请求访问controller之后 渲染试图之前
   *
   * @author luqi
   * @date 2020/4/29
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

  }

  /**
   * 请求访问controller之后 渲染试图之后
   *
   * @author luqi
   * @date 2020/4/29
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {

  }
}
