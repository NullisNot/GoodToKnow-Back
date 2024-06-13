package com.example.goodToKnow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GoodToKnowApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodToKnowApplication.class, args);
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
