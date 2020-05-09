package cn.cloud.ego.search.controller;

import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.base.vo.SearchResult;
import cn.cloud.ego.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/import")
    public EgoResult importAll(){
        return searchService.addDocs(searchService.getDocs(searchService.getData()));
    }

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
    public SearchResult doSearch(String keyword, String categoryName, String price, @RequestParam(defaultValue = "1") Integer page, Integer sort){
        return searchService.doSearch(keyword,categoryName,price,page,sort);
    }

}
