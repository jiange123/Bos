package com.itheima.web.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Table.Cell;
import com.itheima.domain.base.Area;
import com.itheima.domain.base.Courier;
import com.itheima.domain.base.Standard;
import com.itheima.service.base.AreaService;
import com.itheima.utils.FileUtils;
import com.itheima.utils.PinYin4jUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:AreaAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月3日 上午8:45:30 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class AreaAction extends CommonAction<Area> {
    @Autowired
    private AreaService areaService;
    
    private String q;

    public void setQ(String q) {
        this.q = q;
    }

    public AreaAction() {
         super(Area.class);  
    }

    private File file;
    
    public void setFile(File file) {
        this.file = file;
    }

    @Action("areaAction_importFile")
    public String importFile() throws Exception{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        List<Area> list = new ArrayList<>();
        for (Row row : sheet) {
            if(row.getRowNum()==0){
                continue;
            }
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            
            String sub_province = province.substring(0, province.length()-1);
            String sub_city = city.substring(0, city.length()-1);
            String sub_district = district.substring(0, district.length()-1);
            
            String citycode = PinYin4jUtils.hanziToPinyin(sub_city,"").toUpperCase();
            String[] string = PinYin4jUtils.getHeadByString(sub_province+sub_city+sub_district);
            String shortcode = StringUtils.join(string).toUpperCase();
            
            Area area = new Area(province, city, district, postcode, citycode, shortcode);
            list.add(area);
        }
        areaService.save(list);
        hssfWorkbook.close();
        return NONE;
    }
    
    @Action(value="areaAction_pageQuery")
    public String pageQuery() throws Exception{
        
        Specification<Area> specification= new Specification<Area>() {
            @Override
            public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                return null;
            }
        };
        
        Pageable pageable = new PageRequest(page-1, rows);
        Page page1 = areaService.pageQuery(specification,pageable);
        page2json(page1,new String[] {"subareas"});
        return NONE;
    }
    
    @Action(value="areaAction_findAll")
    public String findAll() throws Exception{
        List<Area> list;
        if(StringUtils.isEmpty(q)){
            list= areaService.findAll();
        }else {
            list= areaService.findAll(q);
        }
        list2json(list, new String[]{"subareas"});
        return NONE;
    }
    
    @Action(value="areaAction_exportExcel")
    public String exportExcel() throws Exception{
        List<Area> list=areaService.findAll();
        HSSFWorkbook Workbook = new HSSFWorkbook();
        HSSFSheet sheet = Workbook.createSheet("第一页");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("省");
        row.createCell(1).setCellValue("市");
        row.createCell(2).setCellValue("区");
        row.createCell(3).setCellValue("邮编");
        row.createCell(4).setCellValue("简码");
        row.createCell(5).setCellValue("城市编码");
        for (Area area : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(area.getProvince());
            dataRow.createCell(1).setCellValue(area.getCity());
            dataRow.createCell(2).setCellValue(area.getDistrict());
            dataRow.createCell(3).setCellValue(area.getPostcode());
            dataRow.createCell(4).setCellValue(area.getShortcode());
            dataRow.createCell(5).setCellValue(area.getCitycode());
        }
        
        String fileName="区域数据.xls";
        
        HttpServletRequest request = ServletActionContext.getRequest();
        //获得浏览器名称
        String agent = request.getHeader("User-Agent");
        // 对文件名重新进行编码
        fileName = FileUtils.encodeDownloadFilename(fileName, agent);

        HttpServletResponse response = ServletActionContext.getResponse();
        // 一个流两个头
        // 设置文件的类型
        response.setContentType(ServletActionContext.getServletContext().getMimeType(fileName));
        // 设置Content-Disposition头信息
        response.setHeader("Content-Disposition",
                "attachment; filename=" + fileName);
        
        ServletOutputStream outputStream = response.getOutputStream();
        
        Workbook.write(outputStream);
        
        Workbook.close();
        return NONE;
    }
    
}
  
