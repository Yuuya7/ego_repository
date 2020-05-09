package cn.cloud.ego.manager.service.impl;

import cn.cloud.ego.base.mapper.ItemCatMapper;
import cn.cloud.ego.base.pojo.ItemCat;
import cn.cloud.ego.base.vo.EUTreeNode;
import cn.cloud.ego.manager.service.ItemCatService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {

    /**
     * 商品类型列表
     * @param parentId
     * @return
     */
    @Override
    public List<EUTreeNode> findByParentId(Long parentId) {
        // 1.创建List<EUTreeNode>对象
        List<EUTreeNode> nodes = new ArrayList<>();
        // 2.获取数据
        QueryWrapper<ItemCat> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId).eq("status",1);
        List<ItemCat> itemCats = this.list(wrapper);
        // 3.封装对象
        for (ItemCat itemCat : itemCats) {
            EUTreeNode node = new EUTreeNode();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            // 判断是否是父目录，父目录closed，否则open
            node.setState(itemCat.getIsParent()==1 ? "closed":"open");
            // 添加到集合中
            nodes.add(node);
        }
        // 4.返回结果
        return nodes;
    }
}
