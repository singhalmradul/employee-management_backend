package io.github.singhalmradul.controller;

import io.github.singhalmradul.dao.AttendanceDao;
import io.github.singhalmradul.entity.Attendance;

public class AttendanceController extends AbstractController<Attendance, AttendanceDao> {

    public AttendanceController() {
        super(Attendance.class, AttendanceDao.class);
    }

}
