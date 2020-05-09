package cn.cloud.ego.search.service;

import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.base.vo.SearchItem;
import cn.cloud.ego.base.vo.SearchResult;
import org.apache.solr.common.SolrInputDocument;

import java.util.List;

public interface SearchService {

    /**
     * 数据采集
     * @return
     */
    List<SearchItem> getData();

    /**
     * 数据转换
     * @param items
     * @return
     */
    List<SolrInputDocument> getDocs(List<SearchItem> items);

    /**
     * 添加到索引库
     * @param docs
     * @return
     */
    EgoResult addDocs(List<SolrInputDocument> docs);

    /**
     * 搜索服务
     * @param keyword
     * @param categoryName
     * @param price
     * @param page
     * @param sort
     * @return
     */
    SearchResult doSearch(String keyword, String categoryName, String price, Integer page, Integer sort);

}
