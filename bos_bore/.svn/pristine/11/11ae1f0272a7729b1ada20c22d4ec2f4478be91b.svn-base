package com.itheima.web.action.take_delivery;


import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.take_delivery.Order;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


/**  
 * ClassName:SubareaAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午9:04:11 <br/>  
 * 
 * 
 *      
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
    private static final long serialVersionUID = 1L;
    
    private Order model=new Order();
    
    @Override
    public Order getModel() {
        return model;
    }
    
    @Action(value="orderAction_saveOrder", results ={@Result(name="success" ,
            location="index.html" ,type="redirect" )})
    public String saveOrder(){
        WebClient.create("http://localhost:8081/web/webservice/orderService/saveOrder")
        .type(MediaType.APPLICATION_JSON)
        .post(model);
        return SUCCESS;
    }


}
  
