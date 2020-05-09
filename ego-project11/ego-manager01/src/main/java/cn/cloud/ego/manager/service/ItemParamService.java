package cn.cloud.ego.manager.service;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.base.pojo.ItemParam;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ItemParamService extends IService<ItemParam> {

    /**
     * 规格参数列表
     * @param index
     * @param size
     * @return
     */
    EUDataGirdResult findByPage(Integer index,Integer size);

    /**
     * 查询是否已经存在该模板
     * @param itemCatId
     * @return
     */
    EgoResult findByItemCatId(Long itemCatId);

    /**
     * 添加商品规格参数
     * @param cid
     * @param paramData
     * @return
     */
    EgoResult saveItemParam(Long cid,String paramData);

}
