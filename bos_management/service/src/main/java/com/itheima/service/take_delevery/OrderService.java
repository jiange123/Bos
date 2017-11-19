package com.itheima.service.take_delevery;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.domain.base.Area;
import com.itheima.domain.base.Courier;
import com.itheima.domain.take_delivery.Order;

/**  
 * ClassName:AreaService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月3日 上午9:53:17 <br/>       
 */

@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public interface OrderService {
    
    @POST
    @Path("/saveOrder")
    void saveOrder(Order order);


}
  
