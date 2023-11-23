package io.github.singhalmradul.dao;

import org.hibernate.Session;

import io.github.singhalmradul.entity.Meeting;

public class MeetingDao extends AbstractDao<Meeting> {

    public MeetingDao(Session session) {
        super(session, Meeting.class);
    }

}
