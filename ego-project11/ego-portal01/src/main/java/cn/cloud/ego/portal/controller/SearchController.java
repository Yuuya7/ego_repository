package cn.cloud.ego.portal.controller;

import cn.cloud.ego.base.vo.SearchResult;
import cn.cloud.ego.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping(value = "search")
    public String search(String q, @RequestParam(defaultValue = "1") Integer page, Model model){

        SearchResult result = searchService.doSearch(q, page);

        model.addAttribute("itemList",result.getItemList());
        model.addAttribute("recordCount",result.getRecordCount());
        model.addAttribute("page",page);
        model.addAttribute("totalPage",result.getTotalPage());

        return "search";

    }

}
