package cn.cloud.ego.portal.service;

import cn.cloud.ego.base.pojo.Item;
import org.springframework.web.bind.annotation.PathVariable;

public interface ItemService {

    Item findById(@PathVariable Long itemId);

}
