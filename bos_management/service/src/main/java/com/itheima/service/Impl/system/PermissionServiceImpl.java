package com.itheima.service.Impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.system.PermissionRepository;
import com.itheima.domain.system.Permission;
import com.itheima.service.system.PermissionService;

/**  
 * ClassName:MenuServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月16日 下午6:57:12 <br/>       
 */

@Transactional
@Service
public class PermissionServiceImpl implements PermissionService {
    
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Page pageQuery(Pageable pageable) {
        Page<Permission> page = permissionRepository.findAll(pageable);
        return page;
    }

    @Override
    public void save(Permission model) {
        permissionRepository.save(model);  
    }

    @Override
    public List findAll() {
        List<Permission> list = permissionRepository.findAll();  
        return list;
    }
}
  
