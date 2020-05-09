package cn.cloud.ego.sso.security;

import cn.cloud.ego.base.pojo.Permission;
import cn.cloud.ego.base.pojo.User;
import cn.cloud.ego.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.创建UserDetails对象
        SecurityUserDetails userDetails = new SecurityUserDetails();
        // 2.获取用户的信息
        User user = userService.findByUsername(username);
        // 权限信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissions = user.getRole().getPermissions();
        for (Permission permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getPermissionWord()));
        }
        // 3.封装对象
        userDetails.setUser(user);
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setAuthorities(authorities);
        // 4.返回结果
        return userDetails;
    }
}
