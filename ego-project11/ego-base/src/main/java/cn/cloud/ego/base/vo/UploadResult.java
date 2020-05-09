package cn.cloud.ego.base.vo;

import lombok.Data;

@Data
public class UploadResult {

    private Integer error;
    private String url;
    private String message;

}
