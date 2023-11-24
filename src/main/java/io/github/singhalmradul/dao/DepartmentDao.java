package io.github.singhalmradul.dao;

import java.util.List;

import org.hibernate.Session;

import io.github.singhalmradul.entity.Department;
import io.github.singhalmradul.utils.HibernateUtil;

public class DepartmentDao extends AbstractDao<Department> {

    public DepartmentDao() {
        super(Department.class);
    }

    public List<Department> getDepartmentsByName(String keyword) {
        keyword = "%" + keyword + "%";
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        List<Department> departments = session
                .createQuery("FROM Department WHERE name ILIKE :keyword", Department.class)
                .setParameter("keyword", keyword).getResultList();
        session.getTransaction().commit();
        return departments;
    }
}
