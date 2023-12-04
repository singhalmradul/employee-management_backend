package io.github.singhalmradul.empoyeemanagement.services;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.singhalmradul.empoyeemanagement.entities.Announcement;
import io.github.singhalmradul.empoyeemanagement.entities.Meeting;
import io.github.singhalmradul.empoyeemanagement.repos.AnnouncementRepository;
import io.github.singhalmradul.empoyeemanagement.repos.MeetingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingService {

    private MeetingRepository meetingRepository;
    private AnnouncementRepository announcementRepository;

    @Transactional
    public Meeting createMeeting(Meeting meeting) {
        meeting = meetingRepository.save(meeting);
        for (var employee : meeting.getAttendees()) {
            Announcement announcement = new Announcement();
            announcement.setEmployee(employee);
            announcement.setDuration(Duration.between(LocalTime.now(), meeting.getStartTime()));
            announcement.setText("A new meeting has been scheduled at " + meeting.getStartTime() + " for "
                    + meeting.getDuration().toMinutes() + " minutes.");
            announcement.setTime(LocalTime.now());
            announcement.setTitle("New Meeting Scheduled");
            announcementRepository.save(announcement);
        }
        return meeting;
    }
}
