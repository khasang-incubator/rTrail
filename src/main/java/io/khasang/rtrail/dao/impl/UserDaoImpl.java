package io.khasang.rtrail.dao.impl;

import io.khasang.rtrail.dao.UserDao;
import io.khasang.rtrail.entity.User;

import java.util.List;

/**
 * class realisation method of getByName from Users table of DataBase
 *
 * @author Ilya Bogachev
 * @since 06.06.2018
 */
public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    public List<User> getByName(String username) {
        return (List<User>) getSessionFactory()
                .createQuery("from User as u where u.username = ?").setParameter(0, username).list();
    }
}
