package cn.cloud.ego.base.vo;

import lombok.Data;

import java.util.List;

@Data
public class EUDataGirdResult {

    private Long total;
    private List<?> rows;

}
