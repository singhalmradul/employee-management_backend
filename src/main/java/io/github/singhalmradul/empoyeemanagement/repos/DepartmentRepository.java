package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.empoyeemanagement.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    List<Department> findByNameContainingIgnoreCase(String name);

}
