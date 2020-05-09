package cn.cloud.ego.rest.service.impl;

import cn.cloud.ego.base.mapper.ItemCatMapper;
import cn.cloud.ego.base.pojo.ItemCat;
import cn.cloud.ego.base.vo.Menu;
import cn.cloud.ego.base.vo.MenuNode;
import cn.cloud.ego.rest.service.ItemCatService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {

    @Override
    public Menu getMenu(HttpSession session) {
        // 1.创建Menu对象
        Menu menu = new Menu();
        // 2.获取数据
        // 判断Session是否存在数据，有则从Session中获取，没有则从数据库中获取
        List<Object> data = (List<Object>) session.getAttribute("menu");
        if(data == null){
            // 从数据库获取数据
            data = this.findByParentId(0L);
            // 将数据保存到Session
            session.setAttribute("menu",data);
        }
        // 3.封装Menu
        menu.setData(data);
        // 4.返回结果
        return menu;
    }

    /**
     * 获取商品分类列表
     * @param parentId
     * @return
     */
    private List<Object> findByParentId(Long parentId){
        // 1.创建List<Object>对象
        List<Object> nodes = new ArrayList<>();
        // 2.获取数据
        QueryWrapper<ItemCat> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId).eq("status",1);
        List<ItemCat> itemCats = this.list(wrapper);
        // 3.封装对象
        for (ItemCat itemCat : itemCats) {
            // 判断该分类是否父目录，是则继续迭代，否则直接输出
            if(itemCat.getIsParent() == 1){
                // 创建MenuNode对象
                MenuNode node = new MenuNode();
                node.setU("/products/"+itemCat.getId()+".html");
                node.setN("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
                node.setI(this.findByParentId(itemCat.getId()));
                // 添加到集合中
                nodes.add(node);
            }else{
                nodes.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
            }
        }
        // 4.返回结果
        return nodes;
    }

}
