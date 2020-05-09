package cn.cloud.ego.search.listener;

import cn.cloud.ego.base.vo.SearchItem;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchListener {

    @Autowired
    private HttpSolrServer httpSolrServer;

    @RabbitListener(queues = "ego-item")
    public void listener(SearchItem searchItem){
        // 1.创建SolrInputDocument对象
        SolrInputDocument doc = new SolrInputDocument();
        // 2.封装对象
        doc.setField("id",searchItem.getId());
        doc.addField("item_title",searchItem.getTitle());
        doc.addField("item_category_name",searchItem.getCategoryName());
        doc.addField("item_sell_point",searchItem.getSellPoint());
        doc.addField("item_image",searchItem.getImage());
        doc.addField("item_price",searchItem.getPrice());
        // 添加到索引库
        try {
            httpSolrServer.add(doc);
            httpSolrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
