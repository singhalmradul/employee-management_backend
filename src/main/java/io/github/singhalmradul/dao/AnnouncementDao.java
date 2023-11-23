package io.github.singhalmradul.dao;

import org.hibernate.Session;

import io.github.singhalmradul.entity.Announcement;

public class AnnouncementDao extends AbstractDao<Announcement> {

    public AnnouncementDao(Session session) {
        super(session, Announcement.class);
    }

}
