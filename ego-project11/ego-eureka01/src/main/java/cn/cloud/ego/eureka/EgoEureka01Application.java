package cn.cloud.ego.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EgoEureka01Application {

    public static void main(String[] args) {
        SpringApplication.run(EgoEureka01Application.class, args);
    }

}
