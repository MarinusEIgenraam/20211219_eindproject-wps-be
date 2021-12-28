package com.willpowered.eindprojectwpsbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EindprojectWpsBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EindprojectWpsBeApplication.class, args);
	}

}
