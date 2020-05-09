package cn.cloud.ego.manager.controller;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/find/{id}")
    public Item findById(@PathVariable Integer id){
        return itemService.getById(id);
    }

    /**
     * 商品列表
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/list")
    public EUDataGirdResult list(Integer page, Integer rows){
        return itemService.findByPage(page,rows);
    }

    @PostMapping("/delete")
    @Transactional
    public EgoResult delete(Long[] ids){
        boolean remove = itemService.removeByIds(Arrays.asList(ids));
        if(remove){
            return EgoResult.ok();
        }
        return EgoResult.build(400,"删除失败!");
    }

    /**
     * 添加商品
     * @param item
     * @param desc
     * @param paramData
     * @return
     */
    @PostMapping("/save")
    public EgoResult save(Item item, String desc, String paramData){
        return itemService.insertItem(item,desc,paramData);
    }

}
