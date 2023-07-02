package com.pro.deepak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DircoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DircoveryServerApplication.class, args);
    }
}