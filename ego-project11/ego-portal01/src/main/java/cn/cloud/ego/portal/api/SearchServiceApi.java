package cn.cloud.ego.portal.api;

import cn.cloud.ego.base.vo.SearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ego-search",path = "/search")
public interface SearchServiceApi {

    /**
     * 搜索服务
     * @param keyword
     * @param categoryName
     * @param price
     * @param page
     * @param sort
     * @return
     */
    @GetMapping("/doSearch")
    SearchResult doSearch(@RequestParam String keyword,@RequestParam String categoryName,@RequestParam String price, @RequestParam(defaultValue = "1") Integer page,@RequestParam Integer sort);

}
