package com.itheima.web.action.base;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.boot.registry.StandardServiceInitiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.domain.base.Standard;
import com.itheima.service.base.StandardService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**  
 * ClassName:StandardAction <br/>  
 * Function:  <br/>  
 * Date:     2017年10月31日 下午8:11:25 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class StandardAction extends CommonAction<Standard> {
    
    public StandardAction() {
        super(Standard.class);  
    }

    @Autowired
    private StandardService standardService;
    
    
 /* value = <action>节点中的name属性
     name = <result>节点的name属性
     location = <result>节点中间的路径
     type = 指定使用转发还是重定向
     保存操作
  */
    @Action(value = "standardAction_save", results = {@Result(name ="success" ,
            location="/pages/base/standard.jsp" ,type="redirect")})
    public String save(){
        standardService.save(getModel());
        return SUCCESS;
    }
    
    @Action("standardAction_pageQuery")
    public String pageQuery() throws Exception{
     // PageRequest中的page属性是从0开始的
     // EasyUI传递过来的page属性是从1开始的
        Pageable pageable = new PageRequest(page-1, rows);//特殊pagebean
        
        Page query = standardService.pageQuery(pageable);
        List list = query.getContent();
        Long totalElements = query.getTotalElements();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("total", totalElements.intValue());
        map.put("rows", list);
        String json = JSONObject.fromObject(map).toString();
        
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(json);
        return NONE;
    }
    
    @Action("standardAction_findAll")
    public String findAll() throws Exception{
        List<Standard> list=standardService.findAll(getModel());
        String json = JSONArray.fromObject(list).toString();
        HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(json);
        
        return NONE;
    }
    
    
}
  
