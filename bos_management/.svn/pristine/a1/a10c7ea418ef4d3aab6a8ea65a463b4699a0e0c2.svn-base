package com.itheima.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.domain.system.Role;

/**  
 * ClassName:RoleRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月17日 下午7:44:42 <br/>       
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r inner join r.users u where u.id=?")
    List<Role> findAllByUser(Long id);

}
  
