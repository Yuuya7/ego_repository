package cn.cloud.ego.manager.service;

import cn.cloud.ego.base.pojo.ItemCat;
import cn.cloud.ego.base.vo.EUTreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ItemCatService extends IService<ItemCat> {

    /**
     * 商品类型列表
     * @param parentId
     * @return
     */
    List<EUTreeNode> findByParentId(Long parentId);

}
