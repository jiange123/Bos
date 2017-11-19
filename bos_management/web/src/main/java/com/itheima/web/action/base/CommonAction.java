package com.itheima.web.action.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:CommonAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月3日 上午11:16:28 <br/>       
 */
public class CommonAction<T> extends ActionSupport implements ModelDriven<T> {
    private T modal;
    private Class clazz;
    protected int page;
    protected int rows;
    
    public CommonAction(Class<T> clazz) {
        this.clazz=clazz;
        try {
            modal = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }

    @Override
    public T getModel() {
        return modal;
    }
    
    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    
    public void page2json(Page page1,String[] s) throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        
        Map<Object, Object> map = new HashMap<>();
        map.put("rows", page1.getContent());
        map.put("total", page1.getTotalElements());
        
        JsonConfig config = new JsonConfig();
        config.setExcludes(s);
        
        String json = JSONObject.fromObject(map,config).toString();
        response.getWriter().print(json);
    }
    
    public void list2json(List list,String[] s) throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        
        JsonConfig config = new JsonConfig();
        config.setExcludes(s);
        
        String json = JSONArray.fromObject(list, config).toString();
        response.getWriter().print(json);
    }
    
    
    
}
  
