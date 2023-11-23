package io.github.singhalmradul.controller;

import io.github.singhalmradul.dao.MeetingDao;
import io.github.singhalmradul.entity.Meeting;

public class MeetingController extends AbstractController<Meeting, MeetingDao> {

    public MeetingController() {
        super(Meeting.class, MeetingDao.class);
    }

}
