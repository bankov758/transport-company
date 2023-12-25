package org.example.dao.contracts;

import java.util.List;

public interface CrudDao<T> {

    List<T> getAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);

}
