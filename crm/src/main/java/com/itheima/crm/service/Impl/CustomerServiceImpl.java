package com.itheima.crm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerRepository;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;

/**  
 * ClassName:CustomerServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月6日 上午9:49:15 <br/>       
 */

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public List<Customer> findAll() {
        List<Customer> list = customerRepository.findAll();
        return list;
    }

    @Override
    public List<Customer> findCustomerByNull() {
        List<Customer> list =customerRepository.findCustomerByNull();
        return list;
    }

    @Override
    public List<Customer> findCustomerById(String id) {
        List<Customer> list =customerRepository.findCustomerById(id);  
        return list;
    }

    @Override
    public void assignCustomers2FixedArea(String fid, List<Long> customerIds) {
        System.out.println("fid="+fid);
        customerRepository.updateAssignCustomers(fid);
        if(customerIds!=null&&customerIds.size()>0){
            for (Long cid : customerIds) {
                System.out.println("cid="+cid);
                customerRepository.assignCustomers2FixedArea(fid , cid); 
            }
        }
        
    }

    @Override
    public void regist(Customer customer) {
        customerRepository.save(customer);
        
    }

    @Override
    public void checkCode(String telephone) {
        customerRepository.updateTypeByTelephone(telephone);
    }

    @Override
    public Customer isCheck(String telephone) {
        Customer customer = customerRepository.findByTelephone(telephone); 
        return customer;
    }

    @Override
    public Customer login(String telephone, String password) {
        Customer customer = customerRepository.findByTelephoneAndPassword(telephone, password);  
        return customer;
    }

    @Override
    public List<Customer> findCustomerByFixedareaId(String fixedAreaId) {
        List<Customer> list = customerRepository.findByFixedAreaId(fixedAreaId);  
        return list;
    }

    @Override
    public Long findFixedAreaIdByAddress(String address) {
        return customerRepository.findFixedAreaIdByAddress(address); 
    }

} 
