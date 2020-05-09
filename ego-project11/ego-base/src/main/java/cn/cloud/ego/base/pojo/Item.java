package cn.cloud.ego.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_item")
public class Item implements Serializable {

  @TableId(type = IdType.INPUT)
  private Long id;
  private String title;
  private String sellPoint;
  private Long price;
  private Integer num;
  private String barcode;
  private String image;
  private Long cid;
  private Integer status;
  private Date created;
  private Date updated;
  @TableField(exist = false)
  private String[] images;

  public String[] getImages() {
    if(null != this.image){
      images = this.image.split(",");
    }
    return images;
  }
}
