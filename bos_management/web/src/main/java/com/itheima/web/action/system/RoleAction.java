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

import com.itheima.domain.system.Role;
import com.itheima.service.system.RoleService;
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
public class RoleAction extends CommonAction<Role>  {
    public RoleAction() {
        super(Role.class);  
    }
    
    @Autowired
    private RoleService roleService;
    
    @Action(value="roleAction_pageQuery")
    public String pageQuery() throws Exception{
        Pageable pageable = new PageRequest(page-1, rows);
        Page page1 = roleService.pageQuery(pageable);
        page2json(page1, new String[] {"users","permissions","menus"});
        return NONE;
    }
    
    private String menuIds;
    private List<Long> permissionIds;
    
    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
    
    @Action(value="roleAction_save", results ={@Result(name="success" ,
            location="/pages/system/role.html" ,type="redirect" )})
    public String save() throws Exception{
        roleService.save(getModel(),menuIds,permissionIds);
        return SUCCESS;
    }
    
    @Action(value="roleAction_findAllRole")
    public String findAllRole() throws Exception{
        List<Role> list = roleService.findAllRole();
        list2json(list, new String[] {"users","permissions","menus"});
        return NONE;
    }
    
    
    
    

}
  
