package com.itheima.service.Impl.take_delivery;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.base.AreaRepository;
import com.itheima.dao.base.FixeareaRepository;
import com.itheima.dao.take_delivery.OrderRepository;
import com.itheima.dao.take_delivery.WorkBillRepository;
import com.itheima.domain.base.Area;
import com.itheima.domain.base.Courier;
import com.itheima.domain.base.FixedArea;
import com.itheima.domain.base.SubArea;
import com.itheima.domain.take_delivery.Order;
import com.itheima.domain.take_delivery.WorkBill;
import com.itheima.service.take_delevery.OrderService;

/**  
 * ClassName:CourierServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月1日 上午10:54:09 <br/>       
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private AreaRepository areaRepository;
    
    @Autowired
    private FixeareaRepository fixedAreaRepository;
    
    @Autowired
    private WorkBillRepository workBillRepository;
    
    @Override
    public void saveOrder(Order order) {
        Area sendArea = order.getSendArea();
        Area recArea = order.getRecArea();
        if(sendArea!=null){
            sendArea = areaRepository.findByDistrictAndCityAndProvince(sendArea.getDistrict(),sendArea.getCity(),sendArea.getProvince());
            order.setSendArea(sendArea);
        }
        if(recArea!=null){
            recArea = areaRepository.findByDistrictAndCityAndProvince(recArea.getDistrict(),recArea.getCity(),recArea.getProvince());
            order.setRecArea(recArea);
        }
        order.setOrderNum(RandomStringUtils.randomNumeric(32));
        order.setOrderTime(new Date());
        orderRepository.save(order);
        //可以匹配客户地址的情况-自动分单
        //获得定区id
        Long fixedAreaId = WebClient.create("http://localhost:8181/crm/webservice/customerService/findFixedAreaIdByAddress")
        .type(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .query("address", order.getSendAddress())
        .get(Long.class);
        if(fixedAreaId!=null&&fixedAreaId!=0){
          //获得定区
            FixedArea fixedArea = fixedAreaRepository.findOne(fixedAreaId);
            Set<Courier> couriers = fixedArea.getCouriers();
            if(!couriers.isEmpty()){
                Iterator<Courier> iterator = couriers.iterator();
                Courier courier = iterator.next();
                System.out.println(courier);
                order.setOrderType("自动分单");
                order.setCourier(courier);
                //生成工单
                WorkBill workBill = new WorkBill();
                workBill.setType("新");
                workBill.setPickstate("新单");
                workBill.setBuildtime(new Date());
                workBill.setAttachbilltimes(0);
                workBill.setRemark(order.getRemark());
                workBill.setSmsNumber("121");
                workBill.setCourier(courier);
                workBill.setOrder(order);
                workBillRepository.save(workBill);
                return; 
            }
        }
      //无法匹配客户地址的情况-自动分单
       //获得区域下的分区们 
        if(sendArea!=null){
            Set<SubArea> subareas = sendArea.getSubareas();
            for (SubArea subArea : subareas) {
                //匹配分区关键字
                if(order.getSendAddress().contains(subArea.getAssistKeyWords())||order.getSendAddress().contains(subArea.getKeyWords())){
                    //找到定区
                    FixedArea fixedArea = subArea.getFixedArea();
                    Set<Courier> couriers = fixedArea.getCouriers();
                    if(!couriers.isEmpty()){
                        Iterator<Courier> iterator = couriers.iterator();
                        Courier courier = iterator.next();
                        System.out.println(courier);
                        order.setOrderType("自动分单");
                        order.setCourier(courier);
                        //生成工单
                        WorkBill workBill = new WorkBill();
                        workBill.setType("新");
                        workBill.setPickstate("新单");
                        workBill.setBuildtime(new Date());
                        workBill.setAttachbilltimes(0);
                        workBill.setRemark(order.getRemark());
                        workBill.setSmsNumber("121");
                        workBill.setCourier(courier);
                        workBill.setOrder(order);
                        workBillRepository.save(workBill);
                        return; 
                    }
                }
            }
        }    
        //只能人工分单
        order.setOrderType("人工分单");
    }
}
  
