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

    /**
     * Retrieves an entity of type T from the database based on its ID.
     *
     * @param id The unique identifier of the entity to retrieve.
     * @return The entity of type T if found; otherwise, returns null.
     */
    public T getById(long id) {
        T entity;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            entity = session.get(clazz, id);
            transaction.commit();
        }
        return entity;
    }

    @Override
    public List<T> getAll() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery(format(" from %s ", clazz.getName()), clazz).list();
        }
    }

    /**
     * Retrieves all entities of type T from the database, ordered by a specified field and direction.
     *
     * @param fieldToOrderBy   The field by which the entities should be ordered.
     * @param orderDirection   The direction (ascending or descending) in which the entities should be ordered.
     * @return A List containing all entities of type T in the database, ordered as specified.
     *         If no entities are found, an empty list is returned.
     */
    public List<T> getAll(String fieldToOrderBy, OrderBy orderDirection) {
        String query = format(" from %s order by %s %s ", clazz.getName(), fieldToOrderBy, orderDirection.toString());
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery(query, clazz).list();
        }
    }

    /**
     * Retrieves an entity of type T from the database based on a specified field and its value.
     *
     * @param fieldName The name of the field by which the entity should be retrieved.
     * @param value     The value of the specified field that the retrieved entity should match.
     * @return The entity of type T if found; otherwise, throws an EntityNotFoundException.
     * @throws EntityNotFoundException   If no entity with the specified field and value is found in the database.
     */
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

    @Override
    public void create(T entity) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        }
    }

}
