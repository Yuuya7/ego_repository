package cn.cloud.ego.manager.service;

import cn.cloud.ego.base.pojo.ContentCategory;
import cn.cloud.ego.base.vo.EUTreeNode;
import cn.cloud.ego.base.vo.EgoResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ContentCategoryService extends IService<ContentCategory> {

    /**
     * 内容分类列表
     * @param parentId
     * @return
     */
    List<EUTreeNode> findByParentId(Long parentId);

    /**
     * 添加内容分类
     * @param parentId
     * @param name
     * @return
     */
    EgoResult saveContentCategory(Long parentId,String name);

    /**
     * 编辑内容分类
     * @param id
     * @param name
     * @return
     */
    EgoResult updateContentCategory(Long id,String name);

    /**
     * 删除内容分类
     * @param id
     * @param parentId
     * @return
     */
    EgoResult deleteContentCategory(Long id,Long parentId);

}
