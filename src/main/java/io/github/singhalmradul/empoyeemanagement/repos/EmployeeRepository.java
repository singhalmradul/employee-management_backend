package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.empoyeemanagement.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByJobTitleContainingIgnoreCase(String jobTitle);

}