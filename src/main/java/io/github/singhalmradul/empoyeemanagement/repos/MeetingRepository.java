package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.empoyeemanagement.entities.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, UUID> {

}
