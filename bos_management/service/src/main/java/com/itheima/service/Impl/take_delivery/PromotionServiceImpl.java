package com.itheima.service.Impl.take_delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.take_delivery.OrderRepository;
import com.itheima.dao.take_delivery.PromotionRepository;
import com.itheima.dao.take_delivery.WayBillRepository;
import com.itheima.domain.take_delivery.Promotion;
import com.itheima.domain.take_delivery.WayBill;
import com.itheima.service.take_delevery.PromotionService;
import com.itheima.service.take_delevery.WayBillService;

/**  
 * ClassName:WayBillServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月13日 下午4:09:31 <br/>       
 */
@Transactional
@Service
public class PromotionServiceImpl implements PromotionService {
    
    
    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
        
    }

    @Override
    public Page pageQuery(Pageable pageRequest) {
        Page<Promotion> page = promotionRepository.findAll(pageRequest);  
        return page;
    }

}
  
