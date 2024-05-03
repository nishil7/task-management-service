package com.backend.backendnew;

import com.fasterxml.jackson.databind.MapperFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendnewApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendnewApplication.class, args);
		System.out.println("server running");
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Configuration
	public class JacksonConfig {
		@Bean
		public Jackson2ObjectMapperBuilder objectMapperBuilder() {
			Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
			builder.featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
			return builder;
		}
	}
	@Configuration
	public class CorsConfig {

		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins("http://localhost:3000")
							.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
							.allowedHeaders("*");
				}
			};
		}
	}


}
