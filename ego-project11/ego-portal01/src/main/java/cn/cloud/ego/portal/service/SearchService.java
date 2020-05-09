package cn.cloud.ego.portal.service;

import cn.cloud.ego.base.vo.SearchResult;

public interface SearchService {

    /**
     * 搜索服务
     * @param q
     * @param page
     * @return
     */
    SearchResult doSearch(String q, Integer page);

}
