package cn.cloud.ego.portal.service.impl;

import cn.cloud.ego.base.utils.JsonUtils;
import cn.cloud.ego.base.vo.AdItem;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.portal.api.ContentServiceApi;
import cn.cloud.ego.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired(required = false)
    private ContentServiceApi contentServiceApi;

    @Value("${ego.content.cid}")
    private Long cid;

    @Override
    public String getAdItemList() {
        // 1.获取远程调用的数据
        EgoResult result = contentServiceApi.findByCategoryId(this.cid);
        // 2.封装数据
        List<AdItem> adItems = new ArrayList<>();
        if(result.getStatus() == 200){
            List<Map<String,Object>> maps = (List<Map<String, Object>>) result.getData();
            for (Map<String, Object> map : maps) {
                // 创建AdItem对象
                AdItem adItem = new AdItem();
                // 封装AdItem
                adItem.setSrc((String) map.get("pic"));
                adItem.setSrcB((String) map.get("pic1"));
                adItem.setAlt((String) map.get("titleDesc"));
                adItem.setHref((String) map.get("url"));
                // 添加到集合中
                adItems.add(adItem);
            }
        }
        // 3.返回结果
        String json = JsonUtils.objectToJson(adItems);
        return json;
    }
}
