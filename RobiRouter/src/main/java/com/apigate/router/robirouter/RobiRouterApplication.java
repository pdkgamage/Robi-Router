package com.apigate.router.robirouter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.apigate.router.robirouter")
@SpringBootApplication
public class RobiRouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobiRouterApplication.class, args);
	}
}
