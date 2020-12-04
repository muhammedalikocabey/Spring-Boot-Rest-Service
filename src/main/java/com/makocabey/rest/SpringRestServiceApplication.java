package com.makocabey.rest;


import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringRestServiceApplication {
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(SpringRestServiceApplication.class);
		
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
