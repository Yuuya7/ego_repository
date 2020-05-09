package cn.cloud.ego.manager.service.impl;

import cn.cloud.ego.base.mapper.ItemCatMapper;
import cn.cloud.ego.base.mapper.ItemDescMapper;
import cn.cloud.ego.base.mapper.ItemMapper;
import cn.cloud.ego.base.mapper.ItemParamItemMapper;
import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.base.pojo.ItemCat;
import cn.cloud.ego.base.pojo.ItemDesc;
import cn.cloud.ego.base.pojo.ItemParamItem;
import cn.cloud.ego.base.utils.IDUtils;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.base.vo.SearchItem;
import cn.cloud.ego.manager.service.ItemService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired(required = false)
    private ItemDescMapper itemDescMapper;

    @Autowired(required = false)
    private ItemParamItemMapper itemParamItemMapper;

    @Autowired(required = false)
    private ItemCatMapper itemCatMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;



    /**
     * 商品列表
     * @param start
     * @param size
     * @return
     */
    @Override
    public EUDataGirdResult findByPage(Integer start, Integer size) {
        // 1.创建EUDataGirdResult对象
        EUDataGirdResult result = new EUDataGirdResult();
        // 2.获取数据
        Page<Item> page = this.page(new Page<Item>(start, size));
        // 3.封装对象
        result.setTotal(page.getTotal());
        result.setRows(page.getRecords());
        // 4.返回结果
        return result;
    }

    /**
     * 添加商品
     * @param item
     * @param desc
     * @param paramData
     * @return
     */
    @Override
    @Transactional
    public EgoResult insertItem(Item item, String desc, String paramData) {
        // 1.创建ItemDesc ItemParamItem对象
        ItemDesc itemDesc = new ItemDesc();
        ItemParamItem itemParamItem = new ItemParamItem();
        // 2.封装对象
        Long itemId = IDUtils.genItemId();
        // 封装Item
        item.setId(itemId);
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        // 封装ItemDesc
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        // 封装ItemParamItem
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(paramData);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(itemParamItem.getCreated());
        // 3.添加操作
        boolean saveItem = this.save(item);
        int insertItemDesc = this.itemDescMapper.insert(itemDesc);
        int insertItemParamItem = this.itemParamItemMapper.insert(itemParamItem);
        // 4.推送消息到RabbitMQ中
        if(saveItem && insertItemDesc==1 && insertItemParamItem==1){
            // 创建SearchItem对象
            SearchItem searchItem = new SearchItem();
            // 封装对象
            searchItem.setId(item.getId());
            searchItem.setTitle(item.getTitle());
            searchItem.setSellPoint(item.getSellPoint());
            searchItem.setImage(item.getImage());
            searchItem.setPrice(item.getPrice());
            ItemCat itemCat = itemCatMapper.selectById(item.getCid());
            searchItem.setCategoryName(itemCat.getName());
            // 推送消息
            amqpTemplate.convertAndSend("ego-item",searchItem);
            // 5.返回结果
            return EgoResult.ok(item);
        }
        return EgoResult.build(400,"添加失败!");
    }

}
