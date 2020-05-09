package cn.cloud.ego.base.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchItem implements Serializable {

    private Long id;
    private String title;
    private String categoryName;
    private Long price;
    private String image;
    private String sellPoint;

    private String[] images;

    public String[] getImages() {
        if(image != null){
            images = image.split(",");
        }
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
