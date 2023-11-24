package io.github.singhalmradul.controller;

import io.github.singhalmradul.dao.MeetingDao;
import io.github.singhalmradul.entity.Meeting;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/meetings/*")
public class MeetingController extends AbstractController<Meeting, MeetingDao> {

    public MeetingController() {
        super(Meeting.class, MeetingDao.class);
    }

}
