package com.itheima.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.domain.base.Courier;

/**  
 * ClassName:CourierService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月1日 上午10:53:16 <br/>       
 */
public interface CourierService {

    void save(Courier courier);

    Page pageQuery(Pageable pageable);

    void del(String ids);

    Page pageQuery(Specification<Courier> specification, Pageable pageable);

    List<Courier> findAll();

}
  
