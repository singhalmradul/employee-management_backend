package io.github.singhalmradul.dao;

import org.hibernate.Session;

import io.github.singhalmradul.entity.Attendance;

public class AttendanceDao extends AbstractDao<Attendance> {

    public AttendanceDao(Session session) {
        super(session, Attendance.class);
    }

}
