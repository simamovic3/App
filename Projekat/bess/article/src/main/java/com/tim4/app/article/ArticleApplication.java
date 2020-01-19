package com.tim4.app.article;

import com.tim4.app.article.controllers.FeignController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ArticleApplication {

	public static void main(String[] args)throws RestClientException, IOException {

		ApplicationContext ctx = SpringApplication.run(ArticleApplication.class, args);
	}

	@Bean
	public FeignController feignController() {
		return new FeignController();
	}
}