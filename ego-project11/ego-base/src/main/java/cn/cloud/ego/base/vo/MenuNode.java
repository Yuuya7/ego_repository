package cn.cloud.ego.base.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuNode implements Serializable {

    private String u;
    private String n;
    private List<?> i;

}
