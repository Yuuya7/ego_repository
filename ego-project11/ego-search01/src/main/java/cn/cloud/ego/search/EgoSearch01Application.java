package cn.cloud.ego.search;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class EgoSearch01Application {

    @Value("${solr.baseURL}")
    private String solrBaseURL;

    @Bean
    public HttpSolrServer httpSolrServer(){
        return new HttpSolrServer(solrBaseURL);
    }

    public static void main(String[] args) {
        SpringApplication.run(EgoSearch01Application.class, args);
    }

}
