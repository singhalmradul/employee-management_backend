package io.github.singhalmradul.controller;

import io.github.singhalmradul.dao.AnnouncementDao;
import io.github.singhalmradul.entity.Announcement;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/announcements/*")
public class AnnouncementController extends AbstractController<Announcement, AnnouncementDao> {

    public AnnouncementController() {
        super(Announcement.class, AnnouncementDao.class);
    }
}
