package com.itheima.dao.take_delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.domain.take_delivery.Order;

/**  
 * ClassName:OrderRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月11日 下午8:16:37 <br/>       
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
  
