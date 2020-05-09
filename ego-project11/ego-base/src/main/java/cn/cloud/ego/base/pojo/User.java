package cn.cloud.ego.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("tb_user")
public class User implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;
  private String username;
  private String password;
  private String phone;
  private String email;
  private Date created;
  private Date updated;
  private Long roleId;
  @TableField(exist = false)
  private Role role;
  @TableField(exist = false)
  private List<Modular> modulars;

}
