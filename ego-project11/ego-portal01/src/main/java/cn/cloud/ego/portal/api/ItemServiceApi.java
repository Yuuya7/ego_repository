package cn.cloud.ego.portal.api;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.portal.api.fallback.ItemServiceApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "ego-rest",path = "/rest/item",fallback = ItemServiceApiFallback.class)
public interface ItemServiceApi {

    @GetMapping("/{itemId}.html")
    Item findById(@PathVariable Long itemId);

}
