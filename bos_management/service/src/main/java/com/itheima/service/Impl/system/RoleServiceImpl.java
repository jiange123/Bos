package com.itheima.service.Impl.system;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.system.MenuRepository;
import com.itheima.dao.system.RoleRepository;
import com.itheima.domain.system.Menu;
import com.itheima.domain.system.Permission;
import com.itheima.domain.system.Role;
import com.itheima.service.system.MenuService;
import com.itheima.service.system.RoleService;

/**  
 * ClassName:MenuServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月16日 下午6:57:12 <br/>       
 */

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page pageQuery(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public void save(Role model, String menuIds, List<Long> permissionIds) {
        roleRepository.save(model);
        
        if(StringUtils.isNotEmpty(menuIds)){
            String[] split = menuIds.split(",");
            for (String mid : split) {
                Menu menu = new Menu();
                menu.setId(Long.parseLong(mid));
                model.getMenus().add(menu);
            }
        }
        if(permissionIds!=null&&permissionIds.size()>0){
            for (Long pId : permissionIds) {
                Permission permission = new Permission();
                permission.setId(pId);
                model.getPermissions().add(permission);
            }
        }
        
    }

    @Override
    public List<Role> findAllRole() {
        List<Role> list = roleRepository.findAll();  
        return list;
    }

}
  
