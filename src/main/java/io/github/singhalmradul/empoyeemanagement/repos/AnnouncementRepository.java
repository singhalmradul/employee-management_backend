package io.github.singhalmradul.empoyeemanagement.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.empoyeemanagement.entities.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, UUID> {

}