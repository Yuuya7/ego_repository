package cn.cloud.ego.rest.service.impl;

import cn.cloud.ego.base.mapper.ContentMapper;
import cn.cloud.ego.base.pojo.Content;
import cn.cloud.ego.base.utils.JsonUtils;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.rest.service.ContentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper,Content> implements ContentService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${ego.content.key}")
    private String contentKey;

    @Override
    public EgoResult findByCategoryId(Long cid) {
        // 1.获取数据
        List<Content> contents = new ArrayList<>();
        // 判断Redis中是否存在数据，存在则从Redis获取，不存在则从数据库获取
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        String value = (String) ops.get(this.contentKey, cid + "");
        if(StringUtils.isNotBlank(value)){
            contents = JsonUtils.jsonToList(value,Content.class);
        }else{
            // 从数据库中获取数据
            QueryWrapper<Content> wrapper = new QueryWrapper<>();
            wrapper.eq("category_id",cid);
            contents = this.list(wrapper);
            // 保存到Redis中
            ops.put(this.contentKey,cid+"",JsonUtils.objectToJson(contents));
        }
        // 2.返回结果
        if(contents != null){
            return EgoResult.ok(contents);
        }
        return EgoResult.build(400,"找不到大广告的信息!");
    }
    
}
