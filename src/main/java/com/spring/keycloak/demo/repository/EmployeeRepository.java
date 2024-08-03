package com.spring.keycloak.demo.repository;


import com.spring.keycloak.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
