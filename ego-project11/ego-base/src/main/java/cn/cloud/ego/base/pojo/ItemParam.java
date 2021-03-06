package cn.cloud.ego.base.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_item_param")
public class ItemParam implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;
  private Long itemCatId;
  private String paramData;
  private Date created;
  private Date updated;

}
