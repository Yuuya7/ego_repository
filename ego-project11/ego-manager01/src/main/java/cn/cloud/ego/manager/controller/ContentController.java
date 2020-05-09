package cn.cloud.ego.manager.controller;

import cn.cloud.ego.base.pojo.Content;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 内容列表
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/query/list")
    public EUDataGirdResult list(Long categoryId, Integer page, Integer rows){
        return contentService.findByCategoryIdAndPage(categoryId,page,rows);
    }

    @PostMapping("/delete")
    @Transactional
    public EgoResult delete(Long[] ids){
        boolean remove = contentService.removeByIds(Arrays.asList(ids));
        if(remove){
            return EgoResult.ok();
        }
        return EgoResult.build(400,"删除失败!");
    }

    /**
     * 添加内容
     * @param content
     * @return
     */
    @PostMapping("/save")
    public EgoResult save(Content content){
        return contentService.saveContent(content);
    }

    /**
     * 添加内容
     * @param content
     * @return
     */
    @PostMapping("/edit")
    public EgoResult edit(Content content){
        return contentService.updateContent(content);
    }

}
