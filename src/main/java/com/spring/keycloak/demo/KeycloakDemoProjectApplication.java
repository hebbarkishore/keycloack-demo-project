package com.spring.keycloak.demo;

import com.spring.keycloak.demo.model.Employee;
import com.spring.keycloak.demo.repository.EmployeeRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeycloakDemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakDemoProjectApplication.class, args);
	}

	@Bean
	ApplicationRunner init(EmployeeRepository repository) {
		return (ApplicationArguments args) -> dataSetup(repository);
	}

	private void dataSetup(EmployeeRepository repository) {
		Employee emp1 = new Employee(1, "ksh", "cs");
		Employee emp2 = new Employee(2, "hsk", "ec");
		repository.save(emp1);
		repository.save(emp2);
	}
}
