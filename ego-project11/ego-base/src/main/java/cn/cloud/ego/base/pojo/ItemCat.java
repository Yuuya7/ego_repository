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
@TableName("tb_item_cat")
public class ItemCat implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;
  private Long parentId;
  private String name;
  private Integer status;
  private Integer sortOrder;
  private Integer isParent;
  private Date created;
  private Date updated;

}
