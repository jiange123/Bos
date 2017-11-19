package com.itheima.dao;

import java.util.Collection;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import com.itheima.domain.base.Customer;

/**  
 * ClassName:CustomerTest <br/>  
 * Function:  <br/>  
 * Date:     2017年11月6日 上午10:08:19 <br/>       
 */
public class CustomerTest {
    @Test
    public void test1(){
        //create : 指定请求地址 主机名:端口号/项目名/CXFServlet路径/applicationContext.xml中声明的地址/方法上Path注解声明的地址
        Collection<? extends Customer> collection = WebClient.create("http://localhost:8181/crm/webservice/customerService/findAll")
        .type(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .getCollection(Customer.class);
        for (Customer customer : collection) {
            System.out.println(customer);
        }
    }
}
  
