package cn.cloud.ego.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringCloudApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EgoPortal01Application {

    public static void main(String[] args) {
        SpringApplication.run(EgoPortal01Application.class, args);
    }

}
