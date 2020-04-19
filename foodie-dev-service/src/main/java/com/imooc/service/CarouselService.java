package com.imooc.service;

import com.imooc.pojo.Carousel;
import java.util.List;

public interface CarouselService {

  Carousel getByKey(String id);

  /**
   * 查询所有轮播图列表
   * @param isShow
   * @return
   */
  List<Carousel> queryAll(Integer isShow);
}
