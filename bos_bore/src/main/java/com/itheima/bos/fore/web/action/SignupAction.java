package com.itheima.bos.fore.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import com.itheima.crm.domain.Customer;
import com.itheima.utils.MailUtils;
import com.itheima.utils.SmsUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


/**  
 * ClassName:SignupAction <br/>  
 * Function:  <br/>  
 * Date:     2017年11月7日 下午4:50:04 <br/>       
 */

@Controller
@Namespace("/")
@ParentPackage("json-default")
@Scope("prototype")
public class SignupAction extends ActionSupport implements ModelDriven<Customer> {
    private static final long serialVersionUID = 1L;
    
    private Customer model=new Customer();
    
    @Override
    public Customer getModel() {
        return model;
    }
    
    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Action(value = "signupAction_sendSMS",
            results = {@Result(name = "success", type = "json")})
    public String sendSMS(){
        //验证该手机号码是否已经注册
        Customer customer = WebClient.create("http://localhost:8181/crm/webservice/customerService/isCheck")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .query("telephone",model.getTelephone())
                .get(Customer.class);
                Map<String, String> map = new HashMap<>();
        if(customer==null){
            //生成随机验证码
            String code = RandomStringUtils.randomNumeric(6);
            System.out.println(code);
            //存入session
            ServletActionContext.getRequest().getSession().setAttribute(model.getTelephone(), code);
            final String msg = "尊敬的客户你好，您本次获取的验证码为：" + code;
            jmsTemplate.send("duanxin", new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    MapMessage message = session.createMapMessage();
                    message.setString("msg", msg);
                    message.setString("telephone", model.getTelephone());
                    return message;
                }
            });
            
            //String sendSmsByWebService = SmsUtils.sendSmsByWebService(model.getTelephone(), msg);
            //System.out.println(sendSmsByWebService);
            map.put("result", "yes");
        }else {
            map.put("result", "no");
        }
        ActionContext.getContext().getValueStack().push(map);
        return SUCCESS;
    }
    
    //注入redis模板
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private String checkcode;
    
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Action(value="signupAction_regist", 
            results ={@Result(name="success" ,location="signup-success.html" ,type="redirect" ),
                      @Result(name="fail" ,location="signup-fail.html" ,type="redirect" )
                    })
    public String regist(){
        String code = (String) ServletActionContext.getRequest().getSession().getAttribute(model.getTelephone());
        if(StringUtils.isNotEmpty(checkcode)&&checkcode.equalsIgnoreCase(code)){
            WebClient.create("http://localhost:8181/crm/webservice/customerService/regist")
            .type(MediaType.APPLICATION_JSON)
            .post(model);
            
            //生成随机验证码
            String checkcode = RandomStringUtils.randomNumeric(32);
            //存入redis
            redisTemplate.opsForValue().set(model.getTelephone(),checkcode);
            //发送邮件
            String emailBody="恭喜,接下来您需要通过<a href='http://localhost:8281/bos_bore/signupAction_checkCode.action?telephone="+model.getTelephone()+"&checkcode="+checkcode+"'>点击</a>来激活账户";
            MailUtils.sendMail(model.getEmail(), "用户激活", emailBody);
            return SUCCESS;
        }
        return "fail";
    }
    
    @Action(value="signupAction_checkCode", 
            results ={@Result(name="success" ,location="check-success.html" ,type="redirect" ),
                      @Result(name="active" ,location="check-active.html" ,type="redirect" ),
                      @Result(name="fail" ,location="check-fail.html" ,type="redirect" )
                    })
    public String checkCode(){
        String code = redisTemplate.opsForValue().get(model.getTelephone());
        //验证码是否匹配
        if(StringUtils.isNotEmpty(checkcode)&&checkcode.equals(code)){
            //调用crm查询手机是否已经激活
            Customer customer = WebClient.create("http://localhost:8181/crm/webservice/customerService/isCheck")
            .type(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .query("telephone",model.getTelephone())
            .get(Customer.class);
            if(customer!=null){
                if(customer.getType()==null){
                    //如果没有则激活
                    WebClient.create("http://localhost:8181/crm/webservice/customerService/checkCode")
                    .type(MediaType.APPLICATION_JSON)
                    .query("telephone",model.getTelephone())
                    .put(null);
                    return SUCCESS;
                    
                }
                return "active";
            }
        }
        return "fail";
    }
}
  
