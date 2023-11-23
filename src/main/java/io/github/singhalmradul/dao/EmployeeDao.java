package io.github.singhalmradul.dao;

import java.util.List;

import org.hibernate.Session;

import io.github.singhalmradul.entity.Employee;

public class EmployeeDao extends AbstractDao<Employee> {
    private Session session;

    /**
     * @param session
     */
    public EmployeeDao(Session session) {
        super(session, Employee.class);
        this.session = session;
    }

    public List<Employee> getEmployeeByName(String keyword) {
        keyword = "%" + keyword + "%";
        return session.createQuery("FROM Employee WHERE firstName ILIKE :keyword OR lastName ILIKE :keyword",
                Employee.class).setParameter("keyword", keyword).getResultList();
    }

    public List<Employee> getEmployeesByJobTitle(String keyword) {
        keyword = "%" + keyword + "%";
        return session.createQuery("FROM Employee WHERE jobTitle ILIKE :keyword", Employee.class)
                .setParameter("keyword", keyword).getResultList();
    }

}