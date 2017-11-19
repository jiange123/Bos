package com.itheima.activemq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

/**  
 * ClassName:duanXinConsumer <br/>  
 * Function:  <br/>  
 * Date:     2017年11月13日 下午2:58:42 <br/>       
 */

@Component
public class DuanXinConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message; 
        try {
            String msg = mapMessage.getString("msg");
            String telephone = mapMessage.getString("telephone");
            System.out.println(msg+"您的手机号码为"+telephone);
        } catch (JMSException e) {
            e.printStackTrace();  
        }
    }

    
}
  
