package cn.cloud.ego.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("tb_role")
public class Role {

  @TableId(type = IdType.AUTO)
  private Integer roleId;
  private String roleName;
  private String rolePermissions;
  @TableField(exist = false)
  private List<Permission> permissions;

}
