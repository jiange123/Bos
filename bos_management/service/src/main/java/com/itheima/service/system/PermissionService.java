package com.itheima.service.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.system.Permission;

/**  
 * ClassName:PermissionService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月17日 上午10:16:12 <br/>       
 */
public interface PermissionService {

    Page pageQuery(Pageable pageable);

    void save(Permission model);

    List findAll();

}
  
