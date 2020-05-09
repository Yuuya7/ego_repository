package cn.cloud.ego.portal.api.fallback;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.portal.api.ItemServiceApi;
import org.springframework.stereotype.Component;

@Component
public class ItemServiceApiFallback implements ItemServiceApi {

    @Override
    public Item findById(Long itemId) {
        return new Item();
    }

}
