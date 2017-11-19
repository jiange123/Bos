package com.itheima.service.Impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.base.AreaRepository;
import com.itheima.domain.base.Area;
import com.itheima.domain.base.Courier;
import com.itheima.service.base.AreaService;

/**  
 * ClassName:AreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月3日 上午9:54:16 <br/>       
 */

@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public void save(List<Area> list) {
        for (Area area : list) {
            areaRepository.save(area);
        }
    }

    @Override
    public Page pageQuery(Specification<Area> specification, Pageable pageable) {
          
        Page<Area> page = areaRepository.findAll(specification, pageable); 
        return page;
    }

    @Override
    public List<Area> findAll() {
        List<Area> list = areaRepository.findAll();  
        return list;
    }

    @Override
    public List<Area> findAll(String q) {
        String upperCase = q.toUpperCase();
        List<Area> list = areaRepository.findByQ("%"+upperCase+"%");  
        return list;
    }
    
    
    
}
  
