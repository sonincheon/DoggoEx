package com.Doggo.DoggoEx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DoggoExApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoggoExApplication.class, args);
	}

}
