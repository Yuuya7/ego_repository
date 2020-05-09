package cn.cloud.ego.manager.controller;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.manager.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 规格参数列表
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/list")
    public EUDataGirdResult list(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer rows){
        return itemParamService.findByPage(page,rows);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @Transactional
    public EgoResult delete(Long[] ids){
        boolean remove = itemParamService.removeByIds(Arrays.asList(ids));
        if(remove){
            return EgoResult.ok();
        }
        return EgoResult.build(400,"删除失败!");
    }

    /**
     * 查询是否已经存在该模板
     * @param itemCatId
     * @return
     */
    @GetMapping("/query/itemcatid/{itemCatId}")
    public EgoResult findByItemCatId(@PathVariable Long itemCatId){
        return itemParamService.findByItemCatId(itemCatId);
    }

    /**
     * 添加商品规格参数
     * @param cid
     * @param paramData
     * @return
     */
    @PostMapping("/save/{cid}")
    public EgoResult save(@PathVariable Long cid, String paramData){
        return itemParamService.saveItemParam(cid,paramData);
    }

}
