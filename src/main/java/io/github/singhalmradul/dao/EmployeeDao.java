package io.github.singhalmradul.dao;

import java.util.List;

import org.hibernate.Session;

import io.github.singhalmradul.entity.Employee;
import io.github.singhalmradul.utils.HibernateUtil;

public class EmployeeDao extends AbstractDao<Employee> {

    public EmployeeDao() {
        super(Employee.class);
    }

    public List<Employee> getEmployeeByName(String keyword) {
        keyword = "%" + keyword + "%";
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        List<Employee> employees = session
                .createQuery("FROM Employee WHERE firstName ILIKE :keyword OR lastName ILIKE :keyword", Employee.class)
                .setParameter("keyword", keyword).getResultList();
        session.getTransaction().commit();
        return employees;
    }

    public List<Employee> getEmployeesByJobTitle(String keyword) {
        keyword = "%" + keyword + "%";
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        List<Employee> employees = session
                .createQuery("FROM Employee WHERE jobTitle ILIKE :keyword", Employee.class)
                .setParameter("keyword", keyword).getResultList();
        session.getTransaction().commit();
        return employees;
    }

}