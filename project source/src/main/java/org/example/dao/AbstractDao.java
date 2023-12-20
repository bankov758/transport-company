package org.example.dao;

import org.example.configuration.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static java.lang.String.format;

public abstract  class AbstractDao<T> {

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
