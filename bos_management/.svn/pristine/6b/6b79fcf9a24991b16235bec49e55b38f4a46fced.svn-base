package com.itheima.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itheima.domain.system.Menu;

/**  
 * ClassName:MenuRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月16日 下午6:58:29 <br/>       
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
    
    List<Menu> findByparentMenuIsNull();
    
    @Query("select m from Menu m inner join m.roles r inner join r.users u where u.id=?")
    List<Menu> findByUser(Long id); 
    
}
  
