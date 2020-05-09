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
@TableName("tb_content")
public class Content implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;
  private Long categoryId;
  private String title;
  private String subTitle;
  private String titleDesc;
  private String url;
  private String pic;
  private String pic2;
  private String content;
  private Date created;
  private Date updated;

}
