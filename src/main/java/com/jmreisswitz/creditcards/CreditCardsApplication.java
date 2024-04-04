package com.jmreisswitz.creditcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CreditCardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardsApplication.class, args);
	}

}
