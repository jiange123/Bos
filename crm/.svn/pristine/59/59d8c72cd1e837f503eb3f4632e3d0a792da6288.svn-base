package com.itheima.crm.service;

import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月6日 上午9:41:34 <br/>       
 */

public interface CustomerService {
    
    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findAll();
    
    @GET
    @Path("/findCustomerByNull")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findCustomerByNull();
    
    @GET
    @Path("/findCustomerById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Customer> findCustomerById(@QueryParam("cid") String id);
    
    @PUT
    @Path("/assignCustomers2FixedArea")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void assignCustomers2FixedArea(
            @QueryParam("fid") String fid, 
            @QueryParam("customerIds") List<Long> customerIds);
    
    @POST
    @Path("/regist")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void regist(Customer customer);
    
    @GET
    @Path("/isCheck")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Customer isCheck(
            @QueryParam("telephone") String telephone);
    
    @PUT
    @Path("/checkCode")
    @Consumes(MediaType.APPLICATION_JSON)
    public void checkCode(
            @QueryParam("telephone") String telephone);
    
    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer login(
            @QueryParam("telephone") String telephone,
            @QueryParam("password") String password
            );
    
    @GET
    @Path("/findCustomerByFixedareaId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findCustomerByFixedareaId(
            @QueryParam("fixedAreaId") String fixedAreaId
            );
    
    @GET
    @Path("/findFixedAreaIdByAddress")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long findFixedAreaIdByAddress(
            @QueryParam("address") String address
            );
    
}
  
