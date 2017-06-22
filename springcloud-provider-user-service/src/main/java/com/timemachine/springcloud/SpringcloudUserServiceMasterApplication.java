package com.timemachine.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudUserServiceMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudUserServiceMasterApplication.class, args);
	}
}
