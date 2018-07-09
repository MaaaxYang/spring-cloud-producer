package com.springcloud.demo.serviceproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ServiceProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProducerApplication.class, args);
	}
}
