package cn.cloud.ego.base.mapper;

import cn.cloud.ego.base.pojo.ItemParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemParamMapper extends BaseMapper<ItemParam> {

    @Select("SELECT p.id,c.id AS itemCatId,c.name AS itemCatName,p.param_data AS paramData,p.created,p.updated FROM tb_item_param p JOIN tb_item_cat c ON p.item_cat_id = c.id LIMIT ${start},${size}")
    List<Map<String,Object>> findByPage(@Param("start") Integer start,@Param("size") Integer size);

}
