package com.bradesco.creditoimobiliario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CreditoImobiliarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditoImobiliarioApplication.class, args);
	}

}
