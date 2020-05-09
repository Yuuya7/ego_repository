package cn.cloud.ego.manager.service.impl;

import cn.cloud.ego.base.mapper.ItemMapper;
import cn.cloud.ego.base.mapper.ItemParamMapper;
import cn.cloud.ego.base.pojo.ItemParam;
import cn.cloud.ego.base.vo.EUDataGirdResult;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.manager.service.ItemParamService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemParamServiceImpl extends ServiceImpl<ItemParamMapper, ItemParam> implements ItemParamService {

    @Autowired(required = false)
    private ItemParamMapper itemParamMapper;

    /**
     * 规格参数列表
     * @param index
     * @param size
     * @return
     */
    @Override
    public EUDataGirdResult findByPage(Integer index, Integer size) {
        // 1.创建EUDataGirdResult对象
        EUDataGirdResult result = new EUDataGirdResult();
        // 2.获取数据
        Integer start = (index - 1) * size;
        List<Map<String, Object>> maps = itemParamMapper.findByPage(start, size);
        // 3.封装对象
        result.setTotal((long) this.count());
        result.setRows(maps);
        // 4.返回结果
        return result;
    }

    /**
     * 查询是否已经存在该模板
     * @param itemCatId
     * @return
     */
    @Override
    public EgoResult findByItemCatId(Long itemCatId) {
        // 1.获取数据
        QueryWrapper<ItemParam> wrapper = new QueryWrapper<>();
        if(itemCatId != null){
            wrapper.eq("item_cat_id",itemCatId);
        }
        List<ItemParam> itemParams = this.list(wrapper);
        if(itemParams!=null && itemParams.size()>0){
            return EgoResult.ok(itemParams.get(0));
        }
        return EgoResult.build(400,"可以添加");
    }

    /**
     * 添加商品规格参数
     * @param cid
     * @param paramData
     * @return
     */
    @Override
    public EgoResult saveItemParam(Long cid, String paramData) {
        // 1.创建ItemParam对象
        ItemParam itemParam = new ItemParam();
        if(cid!=null && StringUtils.isNotBlank(paramData)){
            itemParam.setItemCatId(cid);
            itemParam.setParamData(paramData);
            itemParam.setCreated(new Date());
            itemParam.setUpdated(itemParam.getCreated());
        }
        // 2.添加操作
        boolean save = this.save(itemParam);
        // 3.返回结果
        if(save){
            return EgoResult.ok(itemParam);
        }
        return EgoResult.build(400,"添加失败");
    }

}
