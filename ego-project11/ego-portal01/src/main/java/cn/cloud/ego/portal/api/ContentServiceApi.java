package cn.cloud.ego.portal.api;

import cn.cloud.ego.base.vo.EgoResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ego-rest",path = "/rest/content")
@Component
public interface ContentServiceApi {

    @GetMapping("/category/{cid}")
    EgoResult findByCategoryId(@PathVariable Long cid);

}
