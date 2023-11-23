package io.github.singhalmradul.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;

import io.github.singhalmradul.entity.AbstractEntity;
import jakarta.transaction.Transactional;

public class AbstractDao<E extends AbstractEntity> {
    private Session session;
    private Class<E> entityClass;

    /**
     * @param session
     */
    public AbstractDao(Session session, Class<E> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }

    @Transactional
    public UUID save(E entity) {
        session.persist(entity);
        return entity.getId();
    }

    @Transactional
    public UUID update(E entity) {
        session.merge(entity);
        return entity.getId();
    }

    @Transactional
    public UUID delete(UUID id) {
        E entity = session.get(entityClass, id);
        if (entity == null) {
            throw new IllegalArgumentException(entityClass.getSimpleName() + " with id " + id + " not found");
        }
        session.remove(entity);
        return entity.getId();
    }

    public E getById(UUID id) {
        return session.get(entityClass, id);
    }

    public List<E> getAll() {
        return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }

}