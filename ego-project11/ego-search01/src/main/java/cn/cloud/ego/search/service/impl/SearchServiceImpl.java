package cn.cloud.ego.search.service.impl;

import cn.cloud.ego.base.mapper.ItemMapper;
import cn.cloud.ego.base.vo.EgoResult;
import cn.cloud.ego.base.vo.SearchItem;
import cn.cloud.ego.base.vo.SearchResult;
import cn.cloud.ego.search.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private HttpSolrServer httpSolrServer;

    @Autowired(required = false)
    private ItemMapper itemMapper;

    @Value("${solr.pageSize}")
    private Integer pageSize;

    /**
     * 数据采集
     * @return
     */
    @Override
    public List<SearchItem> getData() {
        // 1.获取List<SearchItem>对象
        List<SearchItem> items = itemMapper.getData();
        // 2.返回结果
        return items;
    }

    /**
     * 数据转换
     * @param items
     * @return
     */
    @Override
    public List<SolrInputDocument> getDocs(List<SearchItem> items) {
        // 1.创建List<SolrInputDocument>集合
        List<SolrInputDocument> docs = new ArrayList<>();
        // 2.封装对象
        for (SearchItem item : items) {
            // 创建SolrInputDocument对象
            SolrInputDocument doc = new SolrInputDocument();
            // 封装SolrInputDocument
            doc.setField("id",item.getId());
            doc.addField("item_title",item.getTitle());
            doc.addField("item_category_name",item.getCategoryName());
            doc.addField("item_sell_point",item.getSellPoint());
            doc.addField("item_image",item.getImage());
            doc.addField("item_price",item.getPrice());
            // 添加到集合中
            docs.add(doc);
        }
        // 3.返回结果
        return docs;
    }

    /**
     * 添加到索引库
     * @param docs
     * @return
     */
    @Override
    public EgoResult addDocs(List<SolrInputDocument> docs) {
        try {
            httpSolrServer.add(docs);
            httpSolrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return EgoResult.build(400,"添加失败!");
        }
        return EgoResult.ok();
    }

    /**
     * 搜索服务
     * @param keyword
     * @param categoryName
     * @param price
     * @param page
     * @param sort
     * @return
     */
    @Override
    public SearchResult doSearch(String keyword, String categoryName, String price, Integer page, Integer sort) {
        // 1.创建SearchResult对象
        SearchResult searchResult = new SearchResult();
        try {
            // 2.设置检索参数
            SolrQuery query = this.getSolrQuery(keyword, categoryName, price, page, sort);
            // 3.检索数据
            QueryResponse response = httpSolrServer.query(query);
            SolrDocumentList docs = response.getResults();
            // 支持高亮关键词
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            // 4.封装对象
            List<SearchItem> items = new ArrayList<>();
            for (SolrDocument doc : docs) {
                // 创建SearchItem对象
                SearchItem item = new SearchItem();
                Map<String, List<String>> map = highlighting.get(doc.get("id"));
                if(map != null && map.size() > 0){
                    List<String> itemTitle = map.get("item_title");
                    if(itemTitle != null && itemTitle.size() > 0){
                        item.setTitle(itemTitle.get(0));
                    }
                }else{
                    item.setTitle((String) doc.get("item_title"));
                }
                // 封装SearchItem
                item.setId(Long.parseLong(doc.get("id")+""));
                item.setCategoryName((String) doc.get("item_category_name"));
                item.setSellPoint((String) doc.get("item_sell_point"));
                item.setImage((String) doc.get("item_image"));
                item.setPrice((long) doc.get("item_price"));
                // 添加到集合中
                items.add(item);
            }
            // 封装SearchResult
            searchResult.setItemList(items);
            searchResult.setRecordCount(docs.getNumFound());
            searchResult.setCurPage(page);
            searchResult.setTotalPage((int) Math.ceil(searchResult.getRecordCount() / this.pageSize));
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        // 5.返回结果
        return searchResult;
    }

    /**
     * 设置检索参数
     * @return
     */
    private SolrQuery getSolrQuery(String keyword, String categoryName, String price, Integer page, Integer sort){
        // 1.创建SolrQuery对象
        SolrQuery query = new SolrQuery();
        // 2.设置参数
        // 关键词
        if(StringUtils.isNotBlank(keyword)){
            query.set("q","item_title:"+keyword);
        }else{
            query.set("q","item_title:*");
        }
        // 支持高亮关键词
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<font style='color:red;'>");
        query.setHighlightSimplePost("</font>");
        // 商品类型名称
        if(StringUtils.isNotBlank(categoryName)){
            query.add("fq","item_category_name:"+categoryName);
        }
        // 价格区间
        if(StringUtils.isNotBlank(price)){
            String[] prices = price.split("-");
            if(prices.length == 2){
                query.add("fq","item_price:[ "+prices[0]+" TO "+prices[1]+" ]");
            }else{
                query.add("fq","item_price:[ 0 TO "+prices[0]+" ]");
            }
        }
        // 分页处理
        if(page == null){
            page = 1;
        }
        Integer start = (page - 1) * this.pageSize;
        query.setStart(start);
        query.setRows(this.pageSize);
        // 排序方式
        if(sort != null){
            if(sort == 0){
                query.setSort("item_price", SolrQuery.ORDER.asc); // 升序
            }else{
                query.setSort("item_price", SolrQuery.ORDER.desc); // 降序
            }
        }
        // 3.返回结果
        return query;
    }


}
