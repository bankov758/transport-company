package org.example.dao.contracts;

import java.util.List;

public interface CrudDao<T> {

    /**
     * Retrieves all entities of type T from the database.
     *
     * @return A List containing all entities of type T in the database.
     *         If no entities are found, an empty list is returned.
     */
    List<T> getAll();

    /**
     * Creates a new entity of type T in the database.
     *
     * @param entity The entity to be created in the database.
     */
    void  create(T entity);

    /**
     * Updates an existing entity of type T in the database.
     *
     * @param entity The entity to be updated in the database.
     */
    void update(T entity);

    /**
     * Deletes an existing entity of type T from the database.
     *
     * @param entity The entity to be deleted from the database.
     */
    void delete(T entity);

}
