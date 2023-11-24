package io.github.singhalmradul.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.github.singhalmradul.dao.DepartmentDao;
import io.github.singhalmradul.entity.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/departments/*")
public class DepartmentController extends AbstractController<Department, DepartmentDao> {

    public DepartmentController() {
        super(Department.class, DepartmentDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();
        Map<String, String[]> reqParams = req.getParameterMap();
        try {
            if (reqParams.size() > 0) {
                if (reqParams.containsKey("name")) {
                    List<Department> employees = dao.getDepartmentsByName(reqParams.get("name")[0]);
                    out.print(mapper.writeValueAsString(employees));
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print(mapper.writeValueAsString("Invalid request"));
                }
            } else if (pathInfo == null || pathInfo.equals("/")) {
                List<Department> departments = dao.getAll();
                out.print(mapper.writeValueAsString(departments));
            } else {
                UUID id = UUID.fromString(pathInfo.split("/")[1]);
                Department employee = dao.getById(id);
                out.print(mapper.writeValueAsString(employee));
            }
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(mapper.writeValueAsString(e.getMessage()));
        } finally {
            out.flush();
            out.close();
        }
        super.doGet(req, resp);
    }

}
