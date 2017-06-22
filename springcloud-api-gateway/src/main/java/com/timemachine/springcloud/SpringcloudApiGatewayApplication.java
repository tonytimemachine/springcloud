package com.timemachine.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy//开启zuul的API网关功能
@SpringBootApplication
public class SpringcloudApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudApiGatewayApplication.class, args);
	}
}
