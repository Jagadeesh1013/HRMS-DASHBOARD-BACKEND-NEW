package com.hrmsdashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl("http://10.47.246.92:8080/AgHrmsIntegration/api/germs").build();
	}
}