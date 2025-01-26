package com.quesedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class QuesedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuesedexApplication.class, args);
	}

}
