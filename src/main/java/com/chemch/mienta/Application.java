package com.chemch.mienta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * application to generate break reports
 */
@EnableNeo4jRepositories
@SpringBootApplication()
@EnableWebSecurity
public class Application {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}