package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.singhalmradul.empoyeemanagement.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    @Query("SELECT d FROM Department d WHERE d.name LIKE %:name%")
    List<Department> getDepartmentsByName(@Param("name") String name);
}
