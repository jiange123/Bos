package com.itheima.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.domain.base.SubArea;

/**  
 * ClassName:SubareaService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午9:07:48 <br/>       
 */


public interface SubareaService {

    void save(SubArea model);

    Page pageQuery(Pageable pageRequest);

    List<SubArea> findSubareaByNull();

    List<SubArea> findSubareaById(Long id);

    void assignSubArea2FixedArea(Long id, List<Long> subAreaIds);

    Page<SubArea> findSubareaByFixedareaId(PageRequest pageRequest,
            Specification<SubArea> specification);

}
  
