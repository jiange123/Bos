package com.itheima.service.Impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.system.MenuRepository;
import com.itheima.domain.system.Menu;
import com.itheima.domain.system.User;
import com.itheima.service.system.MenuService;

/**  
 * ClassName:MenuServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月16日 下午6:57:12 <br/>       
 */

@Transactional
@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Page pageQuery(Pageable pageable) {
        Page<Menu> page = menuRepository.findAll(pageable); 
        return page;
    }

    @Override
    public List<Menu> findAllLeverOne() {
        List<Menu> list = menuRepository.findByparentMenuIsNull();  
        return list;
    }

    @Override
    public void save(Menu model) {
        if(model.getParentMenu().getId()==null||model.getParentMenu().getId()==0){
            model.setParentMenu(null);
        }
        menuRepository.save(model);  
    }

    @Override
    public List<Menu> findByUser(User user) {
        List<Menu> list=null;
        if("admin".equals(user.getUsername())){
            list=menuRepository.findAll();
        }else {
            list=menuRepository.findByUser(user.getId());
        }
        return list;
    }
}
  
