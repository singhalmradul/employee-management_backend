package io.github.singhalmradul.dao;

import org.hibernate.Session;

import io.github.singhalmradul.entity.Shift;

public class ShiftDao extends AbstractDao<Shift> {

    public ShiftDao(Session session) {
        super(session, Shift.class);
    }

}
