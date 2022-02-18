package com.expensive_pig.carin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CarinApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarinApplication.class, args);
	}

}
