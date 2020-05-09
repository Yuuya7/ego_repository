package cn.cloud.ego.rest.controller;

import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/category/{cid}")
    public EgoResult findByCategoryId(@PathVariable Long cid){
        return contentService.findByCategoryId(cid);
    }

}
