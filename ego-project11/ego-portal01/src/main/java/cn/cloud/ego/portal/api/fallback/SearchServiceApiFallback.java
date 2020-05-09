package cn.cloud.ego.portal.api.fallback;

import cn.cloud.ego.base.vo.SearchResult;
import cn.cloud.ego.portal.api.SearchServiceApi;
import org.springframework.stereotype.Component;

@Component
public class SearchServiceApiFallback implements SearchServiceApi {
    @Override
    public SearchResult doSearch(String keyword, String categoryName, String price, Integer page, Integer sort) {
        return new SearchResult(null,0L,-1,-1);
    }
}
