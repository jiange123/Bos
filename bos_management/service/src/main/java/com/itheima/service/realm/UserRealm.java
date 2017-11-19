package com.itheima.service.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itheima.dao.system.PermissionRepository;
import com.itheima.dao.system.RoleRepository;
import com.itheima.dao.system.UserRepository;
import com.itheima.domain.system.Permission;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;

/**  
 * ClassName:UserRealm <br/>  
 * Function:  <br/>  
 * Date:     2017年11月14日 下午10:00:04 <br/>       
 */
@Component
public class UserRealm extends AuthorizingRealm {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        
        Subject subject = SecurityUtils.getSubject();
        
        User user = (User) subject.getPrincipal();
        // 查询用户对应的权限和角色
        if("admin".equals(user.getUsername())){
            List<Permission> plist = permissionRepository.findAll();
            for (Permission permission : plist) {
                info.addStringPermission(permission.getKeyword());
            }
            List<Role> rlist = roleRepository.findAll();
            for (Role role : rlist) {
                info.addStringPermission(role.getKeyword());
            }
        }else {
            List<Permission> plist = permissionRepository.findByUser(user.getId());
            for (Permission permission : plist) {
                info.addStringPermission(permission.getKeyword());
            }
            List<Role> rlist = roleRepository.findAllByUser(user.getId());
            for (Role role : rlist) {
                info.addStringPermission(role.getKeyword());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = userRepository.findByUsername(username);
        if(user==null){
            return null;
        }
        /**
         * @param principal    身份,一般出入当前用户
         * @param credentials  凭证,密码(是从数据库中获取到的密码)
         * @param realmName    realm的名字
         */
        AuthenticationInfo authenticationInfo =new SimpleAuthenticationInfo(user, user.getPassword(), getName());    
        return authenticationInfo;
    }
    
    
    
    
    
    
}
  
