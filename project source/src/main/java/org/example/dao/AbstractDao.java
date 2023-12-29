package org.example.dao;

import jakarta.persistence.EntityNotFoundException;
import org.example.configuration.HibernateConfig;
import org.example.dao.contracts.CrudDao;
import org.example.entity.enumeration.OrderBy;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static java.lang.String.format;

public abstract class AbstractDao<T> implements CrudDao<T> {

    private final Class<T> clazz;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void create(T entity) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
    }

    public T getById(long id) {
        T entity;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            entity = session.get(clazz, id);
            transaction.commit();
        }
        return entity;
    }

    public List<T> getAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery(format(" from %s ", clazz.getName()), clazz).list();
        }
    }

    public List<T> getAll(String fieldToOrderBy, OrderBy orderDirection) {
        String query = format(" from %s order by %s %s ", clazz.getName(), fieldToOrderBy, orderDirection.toString());
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery(query, clazz).list();
        }
    }

    public <V> T getByField(String fieldName, V value) {
        String query = format(" from %s where %s = :value", clazz.getSimpleName(), fieldName);
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session
                    .createQuery(query, clazz)
                    .setParameter("value", value)
                    .uniqueResultOptional()
                    .orElseThrow(() -> new EntityNotFoundException(
                            String.format("%s with %s %s was not found!",
                                    clazz.getSimpleName(), fieldName, value)));
        }
    }

    public void update(T entity) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        }
    }

    public void delete(T entity) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }
    }

}
