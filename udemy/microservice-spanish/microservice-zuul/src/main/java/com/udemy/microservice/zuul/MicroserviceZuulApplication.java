package com.udemy.microservice.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import jdk.jfr.Enabled;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class MicroserviceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceZuulApplication.class, args);
	}

}
