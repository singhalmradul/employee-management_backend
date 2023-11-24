package io.github.singhalmradul.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;

import io.github.singhalmradul.entity.AbstractEntity;
import io.github.singhalmradul.utils.HibernateUtil;

public class AbstractDao<E extends AbstractEntity> {
    private Class<E> entityClass;

    public AbstractDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public UUID save(E entity) {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UUID update(E entity) {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
            return entity.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UUID delete(UUID id) {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();
            E entity = session.get(entityClass, id);
            if (entity == null) {
                throw new IllegalArgumentException(entityClass.getSimpleName() + " with id " + id + " not found");
            }
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
            return entity.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public E getById(UUID id) {
        return HibernateUtil.getCurrentSession().get(entityClass, id);
    }

    public List<E> getAll() {

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        List<E> entites = session.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                .getResultList();
        session.getTransaction().commit();
        return entites;
    }
}