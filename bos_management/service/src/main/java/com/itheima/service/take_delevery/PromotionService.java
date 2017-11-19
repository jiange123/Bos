package com.itheima.service.take_delevery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.take_delivery.Promotion;

/**  
 * ClassName:PromotionService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月19日 下午5:42:22 <br/>       
 */
public interface PromotionService {

    void save(Promotion promotion);

    Page pageQuery(Pageable pageRequest);

}
  
