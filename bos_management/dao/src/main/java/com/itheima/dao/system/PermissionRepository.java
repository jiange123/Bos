package com.itheima.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.domain.system.Permission;

/**  
 * ClassName:PermissionRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月17日 上午10:18:06 <br/>       
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>{

    @Query("select p from Permission p inner join p.roles r inner join r.users u where u.id=?")
    List<Permission> findByUser(Long id);

}
  
