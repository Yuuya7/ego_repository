package cn.cloud.ego.manager.controller;

import cn.cloud.ego.base.pojo.ContentCategory;
import cn.cloud.ego.base.vo.EUTreeNode;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired(required = false)
    private ContentCategoryService contentCategoryService;

    /**
     * 内容分类列表
     * @param id
     * @return
     */
    @GetMapping("/list")
    public List<EUTreeNode> list(@RequestParam(defaultValue = "0") Long id){
        return contentCategoryService.findByParentId(id);
    }

    /**
     * 添加内容分类
     * @param parentId
     * @param name
     * @return
     */
    @PostMapping("/create")
    public EgoResult create(Long parentId, String name){
        return contentCategoryService.saveContentCategory(parentId,name);
    }

    /**
     * 编辑内容分类
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/update")
    public EgoResult update(Long id, String name){
        return contentCategoryService.updateContentCategory(id,name);
    }

    /**
     * 删除内容分类
     * @param id
     * @param parentId
     * @return
     */
    @PostMapping("/delete")
    public EgoResult delete(Long id, Long parentId){
        return contentCategoryService.deleteContentCategory(id,parentId);
    }


}
