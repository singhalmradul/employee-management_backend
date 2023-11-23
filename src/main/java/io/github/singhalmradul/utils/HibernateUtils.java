package io.github.singhalmradul.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private SessionFactory sessionFactory;

    private HibernateUtils() {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static HibernateUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final HibernateUtils INSTANCE = new HibernateUtils();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
