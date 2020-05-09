package cn.cloud.ego.portal.controller;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.portal.api.ItemServiceApi;
import cn.cloud.ego.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{itemId}.html")
    public String findById(@PathVariable Long itemId, Model model){
        Item item = itemService.findById(itemId);
        model.addAttribute("item",item);
        return "item";
    }

}
