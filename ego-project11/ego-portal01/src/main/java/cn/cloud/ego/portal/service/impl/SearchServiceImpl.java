package cn.cloud.ego.portal.service.impl;

import cn.cloud.ego.base.vo.SearchResult;
import cn.cloud.ego.portal.api.SearchServiceApi;
import cn.cloud.ego.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchServiceApi searchServiceApi;

    @Override
    public SearchResult doSearch(String q, Integer page) {
        return searchServiceApi.doSearch(q, null, null, page, 1);
    }
}
