package com.itheima.dao.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.domain.system.User;

/**  
 * ClassName:UserRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月14日 下午9:39:20 <br/>       
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    

}
  
