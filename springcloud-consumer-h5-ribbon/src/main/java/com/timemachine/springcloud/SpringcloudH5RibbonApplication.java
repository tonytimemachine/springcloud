package com.timemachine.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudH5RibbonApplication {




	public static void main(String[] args) {
		SpringApplication.run(SpringcloudH5RibbonApplication.class, args);
	}
}
