package com.itheima.service.Impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.base.TakeTimeRepository;
import com.itheima.domain.base.TakeTime;
import com.itheima.service.base.TakeTimeService;

/**  
 * ClassName:TakeTimeServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月6日 下午9:55:28 <br/>       
 */

@Service
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService {
    
    @Autowired
    private TakeTimeRepository takeTimeRepository;
    
    @Override
    public void save(TakeTime model) {
        takeTimeRepository.save(model);
    }

    @Override
    public Page pageQuery(Pageable pageRequest) {
        Page<TakeTime> page = takeTimeRepository.findAll(pageRequest); 
        return page;
    }

    @Override
    public List<TakeTime> findAllTakeTime() {
        List<TakeTime> list = takeTimeRepository.findAll();
        return list;
    }

}
  
