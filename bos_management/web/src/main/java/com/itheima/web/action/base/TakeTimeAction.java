package com.itheima.web.action.base;

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

import com.itheima.domain.base.TakeTime;
import com.itheima.service.base.SubareaService;
import com.itheima.service.base.TakeTimeService;

/**  
 * ClassName:TakeTimeAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月6日 下午9:50:31 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class TakeTimeAction extends CommonAction<TakeTime> {
    private static final long serialVersionUID = 1L;

    public TakeTimeAction() {
        super(TakeTime.class);  
    }
    
    @Autowired
    private TakeTimeService tkeTimeService;
    
    @Action(value="takeTimeAction_save", results ={@Result(name="success" ,
            location="/pages/base/take_time.html" ,type="redirect" )})
    public String save(){
        tkeTimeService.save(getModel());
        return SUCCESS;
    }
    
    @Action(value="takeTimeAction_pageQuery")
    public String pageQuery() throws Exception{
        Pageable pageRequest = new PageRequest(page-1, rows);
        Page page =tkeTimeService.pageQuery(pageRequest);
        page2json(page,null);
        return NONE;
    }
    
    @Action(value="takeTimeAction_findAllTakeTime")
    public String findAllTakeTime() throws Exception{
        List<TakeTime> list=tkeTimeService.findAllTakeTime();
        list2json(list, null);
        return NONE;
    }

}
  
