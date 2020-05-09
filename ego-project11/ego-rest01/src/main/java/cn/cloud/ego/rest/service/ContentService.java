package cn.cloud.ego.rest.service;

import cn.cloud.ego.base.pojo.Content;
import cn.cloud.ego.base.vo.EgoResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ContentService extends IService<Content> {

    EgoResult findByCategoryId(Long cid);

}
