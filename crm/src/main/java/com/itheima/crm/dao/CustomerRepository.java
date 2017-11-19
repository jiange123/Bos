package com.itheima.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月6日 上午9:50:41 <br/>       
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    @Query("from Customer where fixedAreaId is null")
    List<Customer> findCustomerByNull();
    
    @Query("from Customer where fixedAreaId = ?")
    List<Customer> findCustomerById(String id);
    
    @Modifying
    @Query("update Customer set fixedAreaId= null where fixedAreaId = ?")
    void updateAssignCustomers(String id);
    
    @Modifying
    @Query("update Customer set fixedAreaId= ? where id = ?")
    void assignCustomers2FixedArea(String fid, Long cid);
    
    @Modifying
    @Query("update Customer set type= 1 where telephone = ?")
    void updateTypeByTelephone(String telephone);

    Customer findByTelephone(String telephone);
    
    Customer findByTelephoneAndPassword(String telephone,String password);
    
    List<Customer> findByFixedAreaId(String fixedAreaId);
    
    @Query("select fixedAreaId from Customer where address = ?")
    Long findFixedAreaIdByAddress(String address);
}
  
