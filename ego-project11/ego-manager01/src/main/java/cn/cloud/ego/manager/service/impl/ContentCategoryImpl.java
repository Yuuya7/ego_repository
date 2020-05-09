package cn.cloud.ego.manager.service.impl;

import cn.cloud.ego.base.mapper.ContentCategoryMapper;
import cn.cloud.ego.base.pojo.ContentCategory;
import cn.cloud.ego.base.vo.EUTreeNode;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.manager.service.ContentCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements ContentCategoryService {

    /**
     * 内容分类列表
     * @param parentId
     * @return
     */
    @Override
    public List<EUTreeNode> findByParentId(Long parentId) {
        // 1.创建List<EUTreeNode>对象
        List<EUTreeNode> nodes = new ArrayList<>();
        // 2.获取数据
        QueryWrapper<ContentCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId).eq("status",1);
        List<ContentCategory> contentCategories = this.list(wrapper);
        // 3.封装对象
        for (ContentCategory contentCategory : contentCategories) {
            EUTreeNode node = new EUTreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            // 判断是否是父目录，父目录closed，否则open
            node.setState(contentCategory.getIsParent()==1 ? "closed":"open");
            // 添加到集合中
            nodes.add(node);
        }
        // 4.返回结果
        return nodes;
    }

    /**
     * 添加内容分类
     * @param parentId
     * @param name
     * @return
     */
    @Override
    @Transactional
    public EgoResult saveContentCategory(Long parentId, String name) {
        // 1.创建ContentCategory对象
        ContentCategory category = new ContentCategory();
        // 2.封装对象
        if(parentId!=null && StringUtils.isNotBlank(name)){
            category.setParentId(parentId);
            category.setName(name);
            category.setIsParent(0);
            category.setStatus(1);
            category.setCreated(new Date());
            category.setUpdated(category.getCreated());
        }
        // 3.添加操作
        boolean save = this.save(category);
        // 将它的父节点修改成枝节点
        boolean updateParent = false; // 假设修改失败
        if(save){
            ContentCategory parentCategory = this.getById(parentId);
            parentCategory.setIsParent(1);
            parentCategory.setUpdated(new Date());
            updateParent = this.updateById(parentCategory);
        }
        // 4.返回结果
        if(save && updateParent){
            return EgoResult.ok(category);
        }
        return EgoResult.build(400,"修改失败!");
    }

    /**
     * 编辑内容分类
     * @param id
     * @param name
     * @return
     */
    @Override
    @Transactional
    public EgoResult updateContentCategory(Long id, String name) {
        // 1.获取ContentCategory对象
        ContentCategory category = this.getById(id);
        // 2.封装对象
        if(category != null){
            category.setName(name);
            category.setUpdated(new Date());
        }
        // 3.编辑操作
        boolean update = this.updateById(category);
        // 4.返回结果
        if(update){
            return EgoResult.ok(category);
        }
        return EgoResult.build(400,"编辑失败!");
    }

    /**
     * 删除内容分类
     * @param id
     * @param parentId
     * @return
     */
    @Override
    @Transactional
    public EgoResult deleteContentCategory(Long id, Long parentId) {
        // 1.获取ContentCategory对象
        ContentCategory category = this.getById(id);
        if(parentId == null){
            parentId = category.getParentId();
        }
        // 2.删除操作
        // 将内容分类设置删除状态
        category.setStatus(2);
        category.setUpdated(new Date());
        boolean update = this.updateById(category);
        // 判断其父目录是否还有其他的叶节点，没有则将父目录设置叶节点
        if(update){
            QueryWrapper<ContentCategory> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id",parentId).eq("status",1);
            List<ContentCategory> contentCategories = this.list(wrapper);
            // 进行修改
            if(contentCategories==null || contentCategories.size()==0){
                ContentCategory parentCategory = this.getById(parentId);
                parentCategory.setIsParent(0);
                parentCategory.setUpdated(new Date());
                this.updateById(parentCategory);
            }
        }
        // 3.返回结果
        if(update){
            return EgoResult.ok();
        }
        return EgoResult.build(400,"删除失败!");
    }


}
