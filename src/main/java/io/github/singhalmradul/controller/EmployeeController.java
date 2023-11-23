package io.github.singhalmradul.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.singhalmradul.dao.EmployeeDao;
import io.github.singhalmradul.entity.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employees/*")
public class EmployeeController extends AbstractController<Employee, EmployeeDao> {
    public EmployeeController() {
        super(Employee.class, EmployeeDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();
        Map<String, String[]> reqParams = req.getParameterMap();
        try {
            if (reqParams.size() > 0) {
                if (reqParams.containsKey("name")) {
                    List<Employee> employees = dao.getEmployeeByName(reqParams.get("name")[0]);
                    out.print(mapper.writeValueAsString(employees));
                } else if (reqParams.containsKey("jobTitle")) {
                    List<Employee> employees = dao.getEmployeesByJobTitle(reqParams.get("jobTitle")[0]);
                    out.print(mapper.writeValueAsString(employees));
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print(mapper.writeValueAsString("Invalid request"));
                }
            } else if (pathInfo == null || pathInfo.equals("/")) {
                List<Employee> employees = dao.getAll();
                out.print(mapper.writeValueAsString(employees));
            } else {
                UUID id = UUID.fromString(pathInfo.split("/")[1]);
                Employee employee = dao.getById(id);
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
