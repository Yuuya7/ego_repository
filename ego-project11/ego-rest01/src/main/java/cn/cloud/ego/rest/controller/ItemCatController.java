package cn.cloud.ego.rest.controller;

import cn.cloud.ego.base.utils.JsonUtils;
import cn.cloud.ego.base.vo.Menu;
import cn.cloud.ego.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @GetMapping(value = "menu",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public String getMenu(String callback,HttpSession session){
        // 1.获取Menu对象
        Menu menu = itemCatService.getMenu(session);
        // 2.转换成Json
        String jsonMenu = JsonUtils.objectToJson(menu);
        String result = callback + "("+jsonMenu+")";
        // 返回结果
        return result;

    }

}
