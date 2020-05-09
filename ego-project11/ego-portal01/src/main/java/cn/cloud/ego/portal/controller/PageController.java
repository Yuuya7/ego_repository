package cn.cloud.ego.portal.controller;

import cn.cloud.ego.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/{page}.html")
    public String page(@PathVariable String page, Model model){

        if("index".equals(page)){
            String ads = contentService.getAdItemList();
            model.addAttribute("ads",ads);
        }

        return page;
    }

    @GetMapping("/commons/{page}.html")
    public String commonsPage(@PathVariable String page){
        return "/commons/" + page;
    }

}
