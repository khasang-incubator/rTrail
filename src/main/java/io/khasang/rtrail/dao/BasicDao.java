package io.khasang.rtrail.dao;


import io.khasang.rtrail.entity.Cat;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {
    /**
     * for getting session factory
     * @return session factory
     */

    Session getSessionFactory();


    /**
     * method for add entity
     * @param entity new entity to add
     * @return created enitity
     */
    T create(T entity);

    /**
     * method for getting enitty
     * @param id entity' id
     * @return entity
     */

    T getById(long id);

    /**
     * method for getting all entity
     * @return all entity
     */

    List<T> getList();
}
