package com.itheima.dao.base;

import java.util.List;

import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.itheima.domain.base.Area;

/**  
 * ClassName:CourierRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月1日 上午10:58:12 <br/>       
 */
public interface AreaRepository extends JpaRepository<Area, Long> ,JpaSpecificationExecutor<Area> {
    
    @Query("from Area Where province like ?1 or city like ?1 or district like ?1 or citycode like ?1 or shortcode like ?1")
    List<Area> findByQ(String string);
    
    Area findByDistrictAndCityAndProvince(String string, String string2, String string3);

}
  
