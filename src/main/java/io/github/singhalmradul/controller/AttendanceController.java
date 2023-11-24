package io.github.singhalmradul.controller;

import io.github.singhalmradul.dao.AttendanceDao;
import io.github.singhalmradul.entity.Attendance;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/attendance/*")
public class AttendanceController extends AbstractController<Attendance, AttendanceDao> {

    public AttendanceController() {
        super(Attendance.class, AttendanceDao.class);
    }

}
