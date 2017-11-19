package com.itheima.service.Impl.take_delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.take_delivery.OrderRepository;
import com.itheima.dao.take_delivery.WayBillRepository;
import com.itheima.domain.take_delivery.WayBill;
import com.itheima.service.take_delevery.WayBillService;

/**  
 * ClassName:WayBillServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月13日 下午4:09:31 <br/>       
 */
@Transactional
@Service
public class WayBillServiceImpl implements WayBillService {
    
    
    @Autowired
    private WayBillRepository wayBillRepository;

    @Override
    public void save(WayBill model) {
        wayBillRepository.save(model); 
    }
}
  
