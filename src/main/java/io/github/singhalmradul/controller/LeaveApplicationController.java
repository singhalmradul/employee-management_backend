package io.github.singhalmradul.controller;

import io.github.singhalmradul.dao.LeaveApplicationDao;
import io.github.singhalmradul.entity.LeaveApplication;

public class LeaveApplicationController extends AbstractController<LeaveApplication, LeaveApplicationDao> {

    public LeaveApplicationController() {
        super(LeaveApplication.class, LeaveApplicationDao.class);
    }

}