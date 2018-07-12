package br.com.sbMail.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.com.sbMail.*"})
@EntityScan("br.com.sbMail.*")
public class MainApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
