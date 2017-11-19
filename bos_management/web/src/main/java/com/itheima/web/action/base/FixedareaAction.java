package com.itheima.web.action.base;


import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.domain.base.Customer;
import com.itheima.domain.base.FixedArea;
import com.itheima.domain.base.SubArea;
import com.itheima.service.base.FixeareaService;
import com.itheima.service.base.SubareaService;

import net.sf.jasperreports.components.barbecue.BarcodeProviders.NW7Provider;

/**  
 * ClassName:SubareaAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午9:04:11 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class FixedareaAction extends CommonAction<FixedArea>{
    public FixedareaAction() {
        super(FixedArea.class);  
    }
    @Autowired
    private FixeareaService fixeareaService;
    
    @Action(value="fixedareaAction_save", results ={@Result(name="success" ,
            location="/pages/base/fixed_area.html" ,type="redirect" )})
    public String save(){
        fixeareaService.save(getModel());
        return SUCCESS;
    }
    
    private Long courierId;
    private Long takeTimeId;
    
    
    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public void setTakeTimeId(Long takeTimeId) {
        this.takeTimeId = takeTimeId;
    }
    
    @Action(value="fixedAreaAction_associationCourierToFixedArea", results ={@Result(name="success" ,
            location="/pages/base/fixed_area.html" ,type="redirect" )})
    public String associationCourierToFixedArea(){
        Long id = getModel().getId();
        fixeareaService.associationCourierToFixedArea(id,courierId,takeTimeId);
        return SUCCESS;
    }
    
    private List<Long> customerIds;

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    @Action(value="fixedareaAction_assignCustomers2FixedArea", results ={@Result(name="success" ,
            location="/pages/base/fixed_area.html" ,type="redirect" )})
    public String assignCustomers2FixedArea(){
        Long fid = getModel().getId();
        WebClient.create("http://localhost:8181/crm/webservice/customerService/assignCustomers2FixedArea")
        .type(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .query("fid", fid)
        .query("customerIds", customerIds).put(null);
        return SUCCESS;
    }
    
    @Action(value="fixedareaAction_pageQuery")
    public String pageQuery() throws Exception{
        Pageable pageRequest = new PageRequest(page-1, rows);
        Page page =fixeareaService.pageQuery(pageRequest);
        page2json(page,new String[]{"operatingTime","operator","operatingCompany","subareas","couriers"});
        return NONE;
    }
    
    @Action(value="fixedareaAction_findCustomerByNull")
    public String findCustomerByNull() throws Exception{
        List<? extends Customer> list = (List<? extends Customer>) WebClient.create("http://localhost:8181/crm/webservice/customerService/findCustomerByNull")
        .accept(MediaType.APPLICATION_JSON)
        .getCollection(Customer.class);
        list2json(list, new String[]{"subareas","couriers"});
        return NONE;
    }
    
    @Action(value="fixedareaAction_findCustomerById")
    public String findCustomerById() throws Exception{
        List<? extends Customer> list = (List<? extends Customer>) WebClient.create("http://localhost:8181/crm/webservice/customerService/findCustomerById")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .query("cid", getModel().getId())
                .getCollection(Customer.class);
        list2json(list, new String[]{"subareas","couriers"});
        return NONE;
    }
    
    private Long fixedAreaId;

    public void setFixedAreaId(Long fixedAreaId) {
        this.fixedAreaId = fixedAreaId;
    }

    @Action(value="fixedareaAction_findCustomerByFixedareaId")
    public String findCustomerByFixedareaId() throws Exception{
        List<? extends Customer> list = (List<? extends Customer>) WebClient.create("http://localhost:8181/crm/webservice/customerService/findCustomerByFixedareaId")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .query("fixedAreaId", fixedAreaId)
                .getCollection(Customer.class);
        list2json(list, null);
        return NONE;
    }
    
}
  
