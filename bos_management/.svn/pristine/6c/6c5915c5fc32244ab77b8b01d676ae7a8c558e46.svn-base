package com.itheima.web.action.take_delevery;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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

import com.itheima.domain.take_delivery.Promotion;
import com.itheima.domain.take_delivery.WayBill;
import com.itheima.service.take_delevery.PromotionService;
import com.itheima.web.action.base.CommonAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:ImageAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月19日 上午10:11:05 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class PromotionAction extends CommonAction<Promotion> {
    private static final long serialVersionUID = 1L;
    
    public PromotionAction() {
        super(Promotion.class);  
    }
    
    @Autowired
    private PromotionService promotionService;
    
    private File titleImgFile;
    //文件名
    private String titleImgFileFileName;
    //文件类型
    private String titleImgFileContentType;
    
    public void setTitleImgFile(File titleImgFile) {
        this.titleImgFile = titleImgFile;
    }

    public void setTitleImgFileFileName(String titleImgFileFileName) {
        this.titleImgFileFileName = titleImgFileFileName;
    }

    public void setTitleImgFileContentType(String titleImgFileContentType) {
        this.titleImgFileContentType = titleImgFileContentType;
    }
    
    @Action(value="promotionAction_save", results ={@Result(name="success" ,
            location="/pages/take_delivery/promotion.html" ,type="redirect" )})
    public String save() throws IOException{
        Promotion promotion = getModel();
        try {
            //生成文件夹的名
            String saveDirPath = "upload";
            // 保存文件的文件夹的绝对磁盘路径
            saveDirPath= ServletActionContext.getServletContext().getRealPath(saveDirPath);
            //后缀
            String suffix = titleImgFileFileName.substring(titleImgFileFileName.lastIndexOf("."));
            //新文件名
            String newFileName =UUID.randomUUID().toString().replaceAll("-", "")+suffix;
            //保存到指定位置
            FileUtils.copyFile(titleImgFile, new File(saveDirPath,newFileName));
            promotion.setTitleImg("/upload/"+newFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        promotion.setStatus("1");
        promotionService.save(promotion);
        return SUCCESS;
    }
    
    @Action(value="promotionAction_pageQuery")
    public String pageQuery() throws Exception{
        Pageable pageRequest = new PageRequest(page-1, rows);
        Page page =promotionService.pageQuery(pageRequest);
        page2json(page,null);
        return NONE;
    }
}