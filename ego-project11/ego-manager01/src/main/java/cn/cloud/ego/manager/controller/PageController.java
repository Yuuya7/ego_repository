package cn.cloud.ego.manager.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return page;
    }

    @PreAuthorize("hasAuthority('ROLE_LIST')")
    @GetMapping("/{page}.html")
    public String pageHtml(@PathVariable String page){
        return page;
    }

}
