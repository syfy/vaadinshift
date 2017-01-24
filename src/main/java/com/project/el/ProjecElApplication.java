package com.project.el;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjecElApplication extends SpringBootServletInitializer  {

	
	   
	
	public static void main(String[] args) {
		SpringApplication.run(ProjecElApplication.class, args);
			}
	  
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjecElApplication.class);
    }    
    
 
}


