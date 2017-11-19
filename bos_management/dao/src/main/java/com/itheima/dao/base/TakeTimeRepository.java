package com.itheima.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.itheima.domain.base.FixedArea;
import com.itheima.domain.base.TakeTime;

/**  
 * ClassName:FixeareaRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午11:03:12 <br/>       
 */
public interface TakeTimeRepository extends JpaRepository<TakeTime, Long> ,JpaSpecificationExecutor<TakeTime> {

}
  
