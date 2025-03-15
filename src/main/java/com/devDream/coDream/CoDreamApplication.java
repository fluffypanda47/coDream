package com.devDream.coDream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CoDreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoDreamApplication.class, args);
	}

}
