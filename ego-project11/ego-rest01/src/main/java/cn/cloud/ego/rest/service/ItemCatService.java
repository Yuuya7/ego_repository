package cn.cloud.ego.rest.service;

import cn.cloud.ego.base.pojo.ItemCat;
import cn.cloud.ego.base.vo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

public interface ItemCatService extends IService<ItemCat> {

    Menu getMenu(HttpSession session);

}
