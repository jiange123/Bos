package com.itheima.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.base.FixedArea;

/**  
 * ClassName:FixeareaService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午10:59:30 <br/>       
 */
public interface FixeareaService {

    void save(FixedArea model);

    Page pageQuery(Pageable pageRequest);

    void associationCourierToFixedArea(Long id, Long courierId, Long takeTimeId);


}
  
