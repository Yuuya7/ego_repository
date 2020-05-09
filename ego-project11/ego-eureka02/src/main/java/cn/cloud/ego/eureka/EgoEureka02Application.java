package cn.cloud.ego.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EgoEureka02Application {

    public static void main(String[] args) {
        SpringApplication.run(EgoEureka02Application.class, args);
    }

}
