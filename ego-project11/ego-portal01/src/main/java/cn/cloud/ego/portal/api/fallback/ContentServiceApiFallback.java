package cn.cloud.ego.portal.api.fallback;

import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.portal.api.ContentServiceApi;
import org.springframework.stereotype.Component;

@Component
public class ContentServiceApiFallback implements ContentServiceApi {

    @Override
    public EgoResult findByCategoryId(Long cid) {
        return EgoResult.build(400,"找不到大广告的信息");
    }
}
