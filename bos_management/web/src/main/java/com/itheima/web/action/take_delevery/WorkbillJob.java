package com.itheima.web.action.take_delevery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itheima.dao.take_delivery.WayBillRepository;
import com.itheima.dao.take_delivery.WorkBillRepository;
import com.itheima.domain.take_delivery.WayBill;
import com.itheima.domain.take_delivery.WorkBill;
import com.itheima.utils.MailUtils;

import groovy.transform.AutoClone;

/**  
 * ClassName:WorkbillJob <br/>  
 * Function:  <br/>  
 * Date:     2017年11月18日 下午8:33:04 <br/>       
 */

@Component
public class WorkbillJob {
    
    @Autowired
    private WorkBillRepository workbillRepository;
    
    public void sendMail(){
        String emailBody = "编号\t工单类型\t取件状态\t快递员<br/>";
        List<WorkBill> list = workbillRepository.findAll();
        for (WorkBill workBill : list) {
            emailBody+=workBill.getId()+"\t"+workBill.getType()+"\t"+workBill.getPickstate()+"\t"+workBill.getCourier().getName()+"<br/>";
        }
        MailUtils.sendMail("lisi@store.com", "工单详情", emailBody);
    }
    
    
}
  
