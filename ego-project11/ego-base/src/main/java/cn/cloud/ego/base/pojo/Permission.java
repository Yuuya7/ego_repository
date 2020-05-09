package cn.cloud.ego.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_permission")
public class Permission {

  @TableId(type = IdType.AUTO)
  private Integer permissionId;
  private Integer modularId;
  private String permissionName;
  private String permissionAction;
  private String permissionWord;
  private Integer permissionParent;

}
