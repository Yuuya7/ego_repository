package cn.cloud.ego.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class EgoZuul01Application {

    public static void main(String[] args) {
        SpringApplication.run(EgoZuul01Application.class, args);
    }

}
