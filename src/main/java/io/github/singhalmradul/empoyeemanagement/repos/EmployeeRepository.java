package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.singhalmradul.empoyeemanagement.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:name% OR e.lastName LIKE %:name%")
    List<Employee> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT e FROM Employee e WHERE e.jobTitle LIKE %:jobTitle%")
    List<Employee> findByJobTitleContainingIgnoreCase(String jobTitle);
}