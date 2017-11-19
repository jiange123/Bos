package com.itheima.service.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.system.Role;

/**  
 * ClassName:RoleService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月17日 下午7:38:48 <br/>       
 */
public interface RoleService {

    Page pageQuery(Pageable pageable);

    void save(Role model, String menuIds, List<Long> permissionIds);

    List<Role> findAllRole();

}
  
