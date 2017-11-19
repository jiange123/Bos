package com.itheima.web.action.system;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
import org.springframework.stereotype.Controller;

import com.itheima.domain.system.User;
import com.itheima.service.system.UserService;
import com.itheima.web.action.base.CommonAction;

/**  
 * ClassName:AreaAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月3日 上午8:45:30 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class UserAction extends CommonAction<User> {
    public UserAction() {
        super(User.class);  
    }

    
    private String checkCode;

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
    
    @Action(value="userAction_login", results ={
            @Result(name="success" ,location="/index.html" ,type="redirect" )
           ,@Result(name="login" ,location="/login.html" ,type="redirect" )
           ,@Result(name="unauthorized" ,location="/unauthorized.html" ,type="redirect" )
    })
    public String login(){
        String code = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if(StringUtils.isNotEmpty(code)&&code.equalsIgnoreCase(checkCode)){
            //获得subject
            Subject subject = SecurityUtils.getSubject();
            //获得令牌。传入账户密码
            AuthenticationToken token = new UsernamePasswordToken(getModel().getUsername(), getModel().getPassword());
            try {
                // 框架提供的登录方法
                subject.login(token);
                User user = (User) subject.getPrincipal();
                ServletActionContext.getRequest().getSession().setAttribute("user", user);
                return SUCCESS;
            } catch (Exception e) {
                return "unauthorized";
            }
        }
        return LOGIN;
    }
    @Action(value="userAction_logout", results ={
            @Result(name="login" ,location="/login.html" ,type="redirect" )
    })
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return LOGIN;
    }
    
    @Autowired
    private UserService userService;
    
    private List<Long> roleIds;
    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
    
    @Action(value="userAction_save", results ={
            @Result(name="login" ,location="/pages/system/userlist.html" ,type="redirect" )
    })
    public String save(){
        userService.save(getModel(),roleIds);
        return LOGIN;
    }
    
    @Action(value="userAction_pageQuery")
    public String pageQuery() throws Exception{
        Pageable pageable = new PageRequest(page-1, rows);
        Page page1 = userService.pageQuery(pageable);
        page2json(page1, new String[] {"roles"});
        return NONE;
    }
}
  
