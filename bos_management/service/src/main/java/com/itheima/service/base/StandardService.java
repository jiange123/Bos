package com.itheima.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.base.Standard;

/**  
 * ClassName:StandardService <br/>  
 * Function:  <br/>  
 * Date:     2017年10月31日 下午8:15:46 <br/>       
 */
public interface StandardService {

    void save(Standard standard);

    Page pageQuery(Pageable pageable);

    List<Standard> findAll(Standard standard);

}
  
