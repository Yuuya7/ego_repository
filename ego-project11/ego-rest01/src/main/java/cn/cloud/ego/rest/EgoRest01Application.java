package cn.cloud.ego.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EgoRest01Application {

    public static void main(String[] args) {
        SpringApplication.run(EgoRest01Application.class, args);
    }

}
