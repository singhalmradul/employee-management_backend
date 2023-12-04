package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import io.github.singhalmradul.empoyeemanagement.entities.Attendance;

@RestResource(path = "attendance", rel = "attendance")
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
}