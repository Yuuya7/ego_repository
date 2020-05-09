package cn.cloud.ego.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {

    private List<SearchItem> itemList;
    private Long recordCount;
    private Integer totalPage;
    private Integer curPage;

}
