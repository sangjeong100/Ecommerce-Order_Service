package com.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EcommerceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceOrderServiceApplication.class, args);
	}

}
