package io.github.singhalmradul.empoyeemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.singhalmradul.empoyeemanagement.entities.Meeting;
import io.github.singhalmradul.empoyeemanagement.services.MeetingService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/meetings")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingController {

    private MeetingService meetingService;

    @PostMapping
    public Meeting createMeeting(@RequestBody Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

}
