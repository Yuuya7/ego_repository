package cn.cloud.ego.manager.service;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ItemService extends IService<Item> {

    /**
     * 商品列表
     * @param start
     * @param size
     * @return
     */
    EUDataGirdResult findByPage(Integer start,Integer size);

    /**
     * 添加商品
     * @param item
     * @param desc
     * @param paramData
     * @return
     */
    EgoResult insertItem(Item item,String desc,String paramData);

}
