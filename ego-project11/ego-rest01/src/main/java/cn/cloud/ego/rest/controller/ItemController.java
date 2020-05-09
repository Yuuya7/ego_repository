package cn.cloud.ego.rest.controller;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{itemId}.html")
    public Item findById(@PathVariable Long itemId){
        return itemService.getById(itemId);
    }

}
