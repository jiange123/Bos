package com.itheima.service.Impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.base.StandardRepository;
import com.itheima.domain.base.Standard;
import com.itheima.service.base.StandardService;

/**  
 * ClassName:StandardServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年10月31日 下午8:17:23 <br/>       
 */

@Service
@Transactional
public class StandardServiceImpl implements StandardService {
    @Autowired
    private StandardRepository standardRepository;

    @Override
    public void save(Standard standard) {
        standardRepository.save(standard);
    }

    @Override
    public Page pageQuery(Pageable pageable) {
        Page<Standard> findAll = standardRepository.findAll(pageable); 
        return findAll;
    }

    @Override
    public List<Standard> findAll(Standard standard) {
        List<Standard> list = standardRepository.findAll();
        return list;
    }
    
    
    
}
  
