package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.empoyeemanagement.entities.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {

}