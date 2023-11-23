package io.github.singhalmradul.dao;

import java.util.List;

import org.hibernate.Session;

import io.github.singhalmradul.entity.Department;

public class DepartmentDao extends AbstractDao<Department> {
    private Session session;

    /**
     * @param session
     */
    public DepartmentDao(Session session) {
        super(session, Department.class);
        this.session = session;
    }

    public List<Department> getDepartmentsByName(String keyword) {
        keyword = "%" + keyword + "%";
        return session.createQuery("FROM Department WHERE name ILIKE :keyword", Department.class)
                .setParameter("keyword", keyword).getResultList();
    }
}
