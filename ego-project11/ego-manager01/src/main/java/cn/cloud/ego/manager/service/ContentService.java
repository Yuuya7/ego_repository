package cn.cloud.ego.manager.service;

import cn.cloud.ego.base.pojo.Content;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ContentService extends IService<Content> {

    /**
     * 内容列表
     * @param cid
     * @param start
     * @param size
     * @return
     */
    EUDataGirdResult findByCategoryIdAndPage(Long cid,Integer start,Integer size);

    /**
     * 添加内容
     * @param content
     * @return
     */
    EgoResult saveContent(Content content);

    /**
     * 编辑内容
     * @param content
     * @return
     */
    EgoResult updateContent(Content content);

}
