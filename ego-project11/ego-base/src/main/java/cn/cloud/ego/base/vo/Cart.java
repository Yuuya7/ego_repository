package cn.cloud.ego.base.vo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;

@Data
public class Cart {

    private String id;
    private String title;
    private Integer num;
    private Long price;
    private String image;
    private String[] images;

    public String[] getImages() {
        if(StringUtils.isNotBlank(image)){
            images = image.split(",");
        }
        return images;
    }

}
