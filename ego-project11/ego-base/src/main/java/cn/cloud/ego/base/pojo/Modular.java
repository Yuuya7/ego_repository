package cn.cloud.ego.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_modular")
public class Modular {

  @TableId(type = IdType.AUTO)
  private Integer modularId;
  private String modularName;
  private Integer modularSort;

}
