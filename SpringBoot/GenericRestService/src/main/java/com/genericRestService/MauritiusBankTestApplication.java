package com.genericRestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class MauritiusBankTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MauritiusBankTestApplication.class, args);
	}

}
