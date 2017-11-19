package com.itheima.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.domain.base.Courier;

/**  
 * ClassName:CourierRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月1日 上午10:58:12 <br/>       
 */
public interface CourierRepository extends JpaRepository<Courier, Long> ,JpaSpecificationExecutor<Courier> {
    
    @Modifying
    @Query("update Courier set deltag='1' where id=?")
    void delByUpdate(Long id);

}
  
