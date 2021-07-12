package com.epam.rd.newsportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
public class NewsPortalApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NewsPortalApplication.class, args);
	}

}
