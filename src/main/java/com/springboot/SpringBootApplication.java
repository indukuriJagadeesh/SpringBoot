package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.springboot.model.FileStorageProperties;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
//@SpringBootApplication(scanBasePackages={"com.example.something", "com.example.application"})
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableJpaAuditing
//@EnableJms
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}