package com.itheima.service.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itheima.domain.system.Menu;
import com.itheima.domain.system.User;

/**  
 * ClassName:MenuService <br/>  
 * Function:  <br/>  
 * Date:     2017年11月16日 下午6:53:12 <br/>       
 */
public interface MenuService {

    Page pageQuery(Pageable pageable);


    List<Menu> findAllLeverOne();

    void save(Menu model);


    List<Menu> findByUser(User user);


}
  
