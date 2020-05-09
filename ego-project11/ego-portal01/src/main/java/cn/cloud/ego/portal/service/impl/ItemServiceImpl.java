package cn.cloud.ego.portal.service.impl;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.portal.api.ItemServiceApi;
import cn.cloud.ego.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemServiceApi itemServiceApi;

    @Override
    public Item findById(Long itemId) {
        return itemServiceApi.findById(itemId);
    }

}
