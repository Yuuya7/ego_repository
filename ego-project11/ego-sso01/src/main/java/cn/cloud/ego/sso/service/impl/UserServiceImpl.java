package cn.cloud.ego.sso.service.impl;

import cn.cloud.ego.base.mapper.ModularMapper;
import cn.cloud.ego.base.mapper.PermissionMapper;
import cn.cloud.ego.base.mapper.RoleMapper;
import cn.cloud.ego.base.mapper.UserMapper;
import cn.cloud.ego.base.pojo.Modular;
import cn.cloud.ego.base.pojo.Permission;
import cn.cloud.ego.base.pojo.Role;
import cn.cloud.ego.base.pojo.User;
import cn.cloud.ego.base.utils.StringToIntegerArrayUtil;
import cn.cloud.ego.sso.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Autowired(required = false)
    private PermissionMapper permissionMapper;

    @Autowired(required = false)
    private ModularMapper modularMapper;

    @Override
    public User findByUsername(String username) {
        // 1.获取User对象
        User user = null;
        if(StringUtils.isNotBlank(username)){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            List<User> users = this.list(wrapper.eq("username", username));
            if(users!=null && users.size()>0){
                user = users.get(0);
            }
        }
        // 2.获取用户的其他信息
        if(user != null){
            // 角色信息
            Role role = this.roleMapper.selectById(user.getRoleId());
            // 权限信息
            Integer[] permissionIds = StringToIntegerArrayUtil.run(role.getRolePermissions());
            List<Permission> permissions = this.permissionMapper.selectBatchIds(Arrays.asList(permissionIds));
            // 模块信息
            // 判断模块是否重复，重复则不再继续添加
            List<Modular> modulars = new ArrayList<>();
            boolean flag = false; // 假设不重复
            for (Permission permission : permissions) {
                Integer modularId = permission.getModularId();
                for (Modular modular : modulars) {
                    if(modular.getModularId() == modularId){
                        flag = true; // 确认已经重复
                        break;
                    }
                }
                if (!flag){
                    Modular modular = modularMapper.selectById(modularId);
                    modulars.add(modular);
                }
            }
            // 3.封装User
            role.setPermissions(permissions);
            user.setRole(role);
            user.setModulars(modulars);
        }
        // 返回结果
        return user;
    }
}
