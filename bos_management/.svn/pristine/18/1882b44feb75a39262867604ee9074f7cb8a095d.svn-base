package com.itheima.web.action.base;


import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

import com.itheima.domain.base.FixedArea;
import com.itheima.domain.base.SubArea;
import com.itheima.service.base.SubareaService;

/**  
 * ClassName:SubareaAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午9:04:11 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class SubareaAction extends CommonAction<SubArea>{
    public SubareaAction() {
        super(SubArea.class);  
    }
    @Autowired
    private SubareaService subareaService;
    
    @Action(value="subareaAction_save", results ={@Result(name="success" ,
            location="/pages/base/sub_area.html" ,type="redirect" )})
    public String save(){
        subareaService.save(getModel());
        return SUCCESS;
    }
    
    @Action(value="subareaAction_pageQuery")
    public String pageQuery() throws Exception{
        Pageable pageRequest = new PageRequest(page-1, rows);
        Page page =subareaService.pageQuery(pageRequest);
        page2json(page,new String[]{"fixedArea","subareas"});
        return NONE;
    }
    
    @Action(value="subareaAction_findSubareaByNull")
    public String findSubareaByNull() throws Exception{
        List<SubArea> list=subareaService.findSubareaByNull();
        list2json(list, new String[]{"fixedArea","area"});
        return NONE;
    }
    
    @Action(value="subareaAction_findSubareaById")
    public String findSubareaById() throws Exception{
        List<SubArea> list=subareaService.findSubareaById(getModel().getId());
        list2json(list, new String[]{"fixedArea","area"});
        return NONE;
    }
    
    private List<Long> subAreaIds;

    public void setSubAreaIds(List<Long> subAreaIds) {
        this.subAreaIds = subAreaIds;
    }

    @Action(value="subAreaAction_assignSubArea2FixedArea", results ={@Result(name="success" ,
            location="/pages/base/fixed_area.html" ,type="redirect" )})
    public String assignSubArea2FixedArea() throws Exception{
        subareaService.assignSubArea2FixedArea(getModel().getId(),subAreaIds);
        return SUCCESS;
    }
    
    
    @Action(value="subareaAction_findSubareaByFixedareaId")
    public String findSubareaByFixedareaId() throws Exception{
        Specification<SubArea> specification=new Specification<SubArea>(){
            @Override
            public Predicate toPredicate(Root<SubArea> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                FixedArea fixedArea = getModel().getFixedArea();
                if(fixedArea!=null&&fixedArea.getId()!=null){
                    Join<Object, Object> join = root.join("fixedArea");
                    Predicate predicate = cb.equal(join.get("id").as(Long.class), fixedArea.getId());
                    return predicate;
                }
                return null;
            }};
        PageRequest pageable = new PageRequest(0, 10);
        Page<SubArea> page=subareaService.findSubareaByFixedareaId(pageable,specification);
        page2json(page, new String[]{"subareas","couriers"});
        return NONE;
    }
    
    
}
  
