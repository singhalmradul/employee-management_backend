package io.github.singhalmradul.dao;

import org.hibernate.Session;

import io.github.singhalmradul.entity.LeaveApplication;

public class LeaveApplicationDao extends AbstractDao<LeaveApplication> {

    public LeaveApplicationDao(Session session) {
        super(session, LeaveApplication.class);
    }

}
