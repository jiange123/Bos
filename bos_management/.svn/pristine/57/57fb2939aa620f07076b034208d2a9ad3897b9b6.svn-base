package com.itheima.service.Impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.base.CourierRepository;
import com.itheima.dao.base.FixeareaRepository;
import com.itheima.dao.base.TakeTimeRepository;
import com.itheima.domain.base.Courier;
import com.itheima.domain.base.FixedArea;
import com.itheima.domain.base.TakeTime;
import com.itheima.service.base.FixeareaService;

/**  
 * ClassName:FixeareaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午10:59:54 <br/>       
 */
@Service
@Transactional
public class FixeareaServiceImpl implements FixeareaService {
    @Autowired
    private FixeareaRepository fixeareaRepository;
    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private TakeTimeRepository takeTimeRepository;

    @Override
    public void save(FixedArea model) {
        fixeareaRepository.save(model);
    }

    @Override
    public Page pageQuery(Pageable pageRequest) {
        Page<FixedArea> page = fixeareaRepository.findAll(pageRequest)  ;
        return page;
    }

    @Override
    public void associationCourierToFixedArea(Long id, Long courierId, Long takeTimeId) {
        Courier courier = courierRepository.findOne(courierId);
        TakeTime takeTime = takeTimeRepository.findOne(takeTimeId);
        FixedArea fixedArea = fixeareaRepository.findOne(id);
        fixedArea.getCouriers().add(courier);
        courier.setTakeTime(takeTime);
    }

    
}
  
