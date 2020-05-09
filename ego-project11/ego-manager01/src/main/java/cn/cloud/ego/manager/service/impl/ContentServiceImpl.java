package cn.cloud.ego.manager.service.impl;

import cn.cloud.ego.base.mapper.ContentMapper;
import cn.cloud.ego.base.pojo.Content;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.manager.service.ContentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    /**
     * 内容列表
     * @param cid
     * @param start
     * @param size
     * @return
     */
    @Override
    public EUDataGirdResult findByCategoryIdAndPage(Long cid,Integer start,Integer size) {
        // 1.创建EUDataGirdResult对象
        EUDataGirdResult result = new EUDataGirdResult();
        // 2.获取数据
        QueryWrapper<Content> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id",cid);
        Page<Content> page = this.page(new Page<Content>(start, size), wrapper);
        // 3.封装对象
        result.setTotal(page.getTotal());
        result.setRows(page.getRecords());
        // 4.返回结果
        return result;
    }

    /**
     * 添加内容
     * @param content
     * @return
     */
    @Override
    public EgoResult saveContent(Content content) {
        // 1.封装Content对象
        if(content != null){
            content.setCreated(new Date());
            content.setUpdated(content.getCreated());
        }
        // 2.添加操作
        boolean result = false; // 假设添加失败
        if(content != null){
            result = this.save(content);
        }
        // 3.返回结果
        if(result){
            return EgoResult.ok(content);
        }
        return EgoResult.build(400,"添加失败!");
    }

    /**
     * 编辑内容
     * @param content
     * @return
     */
    @Override
    public EgoResult updateContent(Content content) {
        // 1.封装Content对象
        if(content != null){
            content.setUpdated(new Date());
        }
        // 2.编辑操作
        boolean result = false; // 假设编辑失败
        if(content != null){
            result = this.updateById(content);
        }
        // 3.返回结果
        if(result){
            return EgoResult.ok(content);
        }
        return EgoResult.build(400,"编辑失败!");
    }
}
