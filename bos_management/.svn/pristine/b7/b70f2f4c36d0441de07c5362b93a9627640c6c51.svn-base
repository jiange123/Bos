package com.itheima.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.domain.base.Area;
import com.itheima.domain.base.Courier;

/**  
 * ClassName:AreaService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月3日 上午9:53:17 <br/>       
 */
public interface AreaService {

    void save(List<Area> list);

    Page pageQuery(Specification<Area> specification, Pageable pageable);

    List<Area> findAll();

    List<Area> findAll(String q);

}
  
