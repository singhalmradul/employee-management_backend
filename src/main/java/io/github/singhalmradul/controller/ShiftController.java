package io.github.singhalmradul.controller;

import io.github.singhalmradul.dao.ShiftDao;
import io.github.singhalmradul.entity.Shift;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/shifts/*")
public class ShiftController extends AbstractController<Shift, ShiftDao> {

    public ShiftController() {
        super(Shift.class, ShiftDao.class);
    }

}
