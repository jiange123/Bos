package com.itheima.service.Impl.base;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.base.CourierRepository;
import com.itheima.domain.base.Courier;
import com.itheima.domain.base.FixedArea;
import com.itheima.service.base.CourierService;

/**  
 * ClassName:CourierServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月1日 上午10:54:09 <br/>       
 */

@Service
@Transactional
public class CourierServiceImpl implements CourierService {
    @Autowired
    private CourierRepository courierRepository;

    @Override
    public void save(Courier courier) {
          
        courierRepository.save(courier);
        
    }

    @Override
    public Page<Courier> pageQuery(Pageable pageable) {
        Page<Courier> page = courierRepository.findAll(pageable); 
        return page;
    }
    
    @RequiresPermissions("region:list")
    @Override
    public void del(String ids) {
        if(StringUtils.isEmpty(ids)){
            return;
        }
        String[] split = ids.split(",");
        for (String id : split) {
            courierRepository.delByUpdate(Long.parseLong(id)); 
        }
    }

    @Override
    public Page pageQuery(Specification<Courier> specification, Pageable pageable) {
        return courierRepository.findAll(specification, pageable);
    }
    
    @Override
    public List<Courier> findAll() {
        List<Courier> list = courierRepository.findAll();  
        return list;
    }
    
}
  
