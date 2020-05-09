package cn.cloud.ego.sso.service;

import cn.cloud.ego.base.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

    /**
     * 根据账号查询用户名称
     * @param username
     * @return
     */
    User findByUsername(String username);

}
