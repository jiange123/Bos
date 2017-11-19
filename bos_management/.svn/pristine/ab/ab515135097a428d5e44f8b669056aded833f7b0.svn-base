package com.itheima.web.action.system;


import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.domain.system.Menu;
import com.itheima.domain.system.Permission;
import com.itheima.service.system.MenuService;
import com.itheima.service.system.PermissionService;
import com.itheima.web.action.base.CommonAction;


/**  
 * ClassName:MenuAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月16日 下午6:36:51 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class PermissionAction extends CommonAction<Permission>  {
    public PermissionAction() {
        super(Permission.class);  
    }
    
    @Autowired
    private PermissionService permissionService;
    
    @Action(value="permissionService_pageQuery")
    public String pageQuery() throws Exception{
        Pageable pageable = new PageRequest(page-1, rows);
        Page page1 = permissionService.pageQuery(pageable);
        page2json(page1, new String[] {"roles"});
        return NONE;
    }
    
    @Action(value="permissionAction_findAllPermission")
    public String findAllPermission() throws Exception{
        List list=permissionService.findAll();
        list2json(list, new String[] {"roles"});
        return NONE;
    }
    
    
    @Action(value="permissionService_save", results ={@Result(name="success" ,
            location="/pages/system/permission.html" ,type="redirect" )})
    public String save() throws Exception{
        permissionService.save(getModel());
        return SUCCESS;
    }
    
    
    
    
    

}
  
