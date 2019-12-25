package com.springboot.sso.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
@EnableWebFluxSecurity
public class SSOServiceApplication {

	 public static void main(String[] args)
	{
		SpringApplication.run(SSOServiceApplication.class, args);
	}






	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
		 http.authorizeExchange()
				.pathMatchers("/app/**")
				.authenticated()
				.and().httpBasic()
				.and().csrf().disable();
		 http.authorizeExchange().anyExchange().permitAll();

		return  http.build();
	}


}