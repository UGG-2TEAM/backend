package org.example.ugg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UggApplication {

	public static void main(String[] args) {
		SpringApplication.run(UggApplication.class, args);
	}

}
