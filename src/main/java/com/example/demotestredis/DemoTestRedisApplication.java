package com.example.demotestredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class DemoTestRedisApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoTestRedisApplication.class, args);
	}

}
