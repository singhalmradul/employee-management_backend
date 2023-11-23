package io.github.singhalmradul.controller;

import io.github.singhalmradul.dao.ShiftDao;
import io.github.singhalmradul.entity.Shift;

public class ShiftController extends AbstractController<Shift, ShiftDao> {

    public ShiftController() {
        super(Shift.class, ShiftDao.class);
    }

}
