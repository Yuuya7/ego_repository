package cn.cloud.ego.base.mapper;

import cn.cloud.ego.base.pojo.Item;
import cn.cloud.ego.base.vo.SearchItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper extends BaseMapper<Item> {

    @Select("select i.id,i.title,c.name as categoryName,i.price,i.image,i.sell_point as sellPoint from tb_item i left join tb_item_cat c on i.cid = c.id where i.status = 1")
    List<SearchItem> getData();

}
