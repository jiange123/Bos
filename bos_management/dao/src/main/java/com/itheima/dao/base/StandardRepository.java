package com.itheima.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.itheima.domain.base.Standard;

/**  
 * ClassName:StandardRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年10月31日 上午11:15:59 <br/>       
 */
public interface StandardRepository extends JpaRepository<Standard, Long>{
    
    
}
  
