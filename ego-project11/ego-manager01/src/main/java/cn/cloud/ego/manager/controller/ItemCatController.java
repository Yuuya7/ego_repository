package cn.cloud.ego.manager.controller;

import cn.cloud.ego.base.vo.EUTreeNode;
import cn.cloud.ego.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @PostMapping("/list")
    public List<EUTreeNode> list(@RequestParam(defaultValue = "0") Long id){
        return itemCatService.findByParentId(id);
    }

}
