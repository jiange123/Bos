package com.itheima.web.action.take_delevery;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.take_delivery.WayBill;
import com.itheima.service.take_delevery.WayBillService;
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
public class WayBillAction extends CommonAction<WayBill> {
    
    public WayBillAction() {
        super(WayBill.class);  
    }

    @Autowired
    private WayBillService wayBillService;
    
    @Action("wayBillAction_save")
    public String save() throws Exception{
        
        String msg;
        try {
            wayBillService.save(getModel());
            msg="yes";
        } catch (Exception e) {
            msg="no";
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(msg);
        return NONE;
    }
    
    
}
  
