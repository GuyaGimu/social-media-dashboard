package com.guya.Social_Media_Dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.guya.Social_Media_Dashboard.models")
@ComponentScan("com.guya.Social_Media_Dashboard")
public class SocialMediaDashboardApplication {

	public static void main(String[] args) {

		SpringApplication.run(SocialMediaDashboardApplication.class, args);
		System.out.println("Hello there");
	}

}
