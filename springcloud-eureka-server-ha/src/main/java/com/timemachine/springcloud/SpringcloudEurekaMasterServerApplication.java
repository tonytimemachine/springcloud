package com.timemachine.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringcloudEurekaMasterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaMasterServerApplication.class, args);
	}
}
