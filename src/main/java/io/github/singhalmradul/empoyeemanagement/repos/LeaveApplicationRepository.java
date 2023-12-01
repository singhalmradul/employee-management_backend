package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.empoyeemanagement.entities.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, UUID> {

}