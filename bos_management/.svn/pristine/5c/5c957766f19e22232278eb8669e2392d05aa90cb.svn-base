package com.itheima.service.Impl.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.system.RoleRepository;
import com.itheima.dao.system.UserRepository;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
import com.itheima.service.system.UserService;

/**  
 * ClassName:UserServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月17日 下午9:58:52 <br/>       
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User model, List<Long> roleIds) {
        userRepository.save(model);
        if(roleIds!=null&&roleIds.size()>0){
            for (Long roleId : roleIds) {
                Role role = new Role();
                role.setId(roleId);
                model.getRoles().add(role);
            }
        }  
    }

    @Override
    public Page pageQuery(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);  
        return page;
    }

}
  
