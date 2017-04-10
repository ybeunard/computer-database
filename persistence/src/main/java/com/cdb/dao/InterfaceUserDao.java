package com.cdb.dao;

import com.cdb.model.entities.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface InterfaceUserDao.
 */
public interface InterfaceUserDao {

    /**
     * Find by user name.
     *
     * @param username
     *            the username
     * @return the user
     */
    User findByUserName(String username);

}
