package cn.cloud.ego.rest.service.impl;

import cn.cloud.ego.base.mapper.ItemMapper;
import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.rest.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
}
