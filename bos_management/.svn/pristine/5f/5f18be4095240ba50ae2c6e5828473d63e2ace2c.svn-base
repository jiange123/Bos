package com.itheima.web.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
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

import com.itheima.domain.base.Courier;
import com.itheima.domain.base.FixedArea;
import com.itheima.domain.base.Standard;
import com.itheima.service.base.CourierService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import groovy.servlet.ServletCategory;
import javassist.expr.NewArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:CourierAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月1日 上午10:47:04 <br/>       
 */
@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class CourierAction extends CommonAction<Courier> {
    private String ids; 
    
    
    public CourierAction() {
          
        super(Courier.class);  
        
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Autowired
    private CourierService courierService;
    
    @Action(value="courierAction_save", results ={@Result(name="success" ,
            location="/pages/base/courier.html" ,type="redirect" )})
    public String save(){
        courierService.save(getModel());
        return SUCCESS;
    }
    
    @Action(value="courierAction_findAllCourier")
    public String findAllCourier() throws Exception{
        List<Courier> list= courierService.findAll();
        list2json(list, new String[]{"fixedAreas"});
        return NONE;
    }
    
    @Action(value="courierAction_del", results ={@Result(name="success" ,
            location="/pages/base/courier.html" ,type="redirect" )})
    public String del(){
        courierService.del(ids);
        return SUCCESS;
    }

//    courierNum  3
//    standard.name   33
//    company 421  单位
//    type  取件员类型
    @Action(value="courierAction_pageQuery")
    public String pageQuery() throws Exception{
        
        Specification<Courier> specification= new Specification<Courier>() {
            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                String courierNum = getModel().getCourierNum();
                String type = getModel().getType();
                String company = getModel().getCompany();
                Standard standard = getModel().getStandard();
                
                ArrayList<Object> list = new ArrayList<>();
                
                if(StringUtils.isNotEmpty(courierNum)){
                    Predicate p1 = cb.equal(root.get("courierNum").as(String.class), courierNum);
                    list.add(p1);
                } 
                if(StringUtils.isNotEmpty(type)){
                    Predicate p2 = cb.equal(root.get("type").as(String.class), type);
                    list.add(p2);
                } 
                if(StringUtils.isNotEmpty(company)){
                    Predicate p3 = cb.like(root.get("company").as(String.class), "%"+company+"%");
                    list.add(p3);
                }
                if(standard!=null){
                    if(StringUtils.isNotEmpty(standard.getName())){
                     // 因为Standard是对象,也就是Courier的关联对象,所以需要先获取关联对象,再去执行查询
                        Join<Object, Object> join = root.join("standard");
                        Predicate p4 = cb.equal(join.get("name").as(String.class), standard.getName());
                        list.add(p4);
                    } 
                }
                if(list.size()==0){
                    return null;
                }else {
                    //集合转数组
                    Predicate[] arr =new Predicate[list.size()];
                    Predicate[] array = list.toArray(arr);
                    //and方法要传入数组
                    Predicate predicate = cb.and(array);
                    return predicate;
                }
                
            }
        };
        Pageable pageable = new PageRequest(page-1, rows);
        Page page1 = courierService.pageQuery(specification,pageable);
        
        page2json(page1, new String[] {"fixedAreas"});
        return NONE;
    }
}
  
