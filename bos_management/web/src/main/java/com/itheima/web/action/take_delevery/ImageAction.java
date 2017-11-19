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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.take_delivery.WayBill;
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
public class ImageAction extends CommonAction<Object> {
    private static final long serialVersionUID = 1L;
    
    public ImageAction() {
        super(Object.class);  
    }
    
    private File imgFile;
    //文件名
    private String imgFileFileName;
    //文件类型
    private String imgFileContentType;
    
    
    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }
    public void setImgFileFileName(String imgFileFileName) {
        this.imgFileFileName = imgFileFileName;
    }
    public void setImgFileContentType(String imgFileContentType) {
        this.imgFileContentType = imgFileContentType;
    }

    @Action("imageAction_upload")
    public String upload() throws IOException{
        Map<String, Object> map = new HashMap<>();
        try {
            //生成文件夹的名
            String saveDirPath = "upload";
            // 保存文件的文件夹的绝对磁盘路径
            saveDirPath= ServletActionContext.getServletContext().getRealPath(saveDirPath);
            //后缀
            String suffix = imgFileFileName.substring(imgFileFileName.lastIndexOf("."));
            //初始文件名
            //String oldFileName = imgFileFileName.substring(0,imgFileFileName.lastIndexOf("."));
            //新文件名
            String newFileName = /*oldFileName+"_"+*/UUID.randomUUID().toString().replaceAll("-", "")+suffix;
            
            //保存到指定位置
            FileUtils.copyFile(imgFile, new File(saveDirPath,newFileName));
            //获得相对路径
            String path = ServletActionContext.getServletContext().getContextPath();
            
            map.put("error", 0);
            map.put("url", path+"/upload/"+newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1); 
            map.put("message", "图片上传出错");  
        }
        
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        String json = JSONObject.fromObject(map).toString();
        response.getWriter().print(json);
        return NONE;
    }
    
    @Action("imageAction_manager")
    public String manager() throws IOException{
        //生成文件夹的名
        String saveDirPath = "upload";
        //保存文件的文件夹的绝对磁盘路径
        saveDirPath= ServletActionContext.getServletContext().getRealPath(saveDirPath);
        File currentPathFile = new File(saveDirPath);
        
        // 图片扩展名
        String[] fileTypes = new String[] {"gif", "jpg", "jpeg", "png", "bmp"};
        
        // 遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<Hashtable>();
        if (currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash =
                        new Hashtable<String, Object>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt =
                            fileName.substring(fileName.lastIndexOf(".") + 1)
                                    .toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo",
                            Arrays.<String>asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(file.lastModified()));
                fileList.add(hash);
            }
        }
        //获得相对路径
        String path = ServletActionContext.getServletContext().getContextPath();
         // 封装写回客户端的数据
        Map<String, Object> map = new HashMap<>();
        // 指定所有文件的信息
        map.put("file_list", fileList);
        // 指定保存文件的文件夹的路径
        map.put("current_url",path + "/upload/");

        // 向客户端写回数据
        String json = JSONObject.fromObject(map).toString();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
        return NONE;

    }

 
    
  
}