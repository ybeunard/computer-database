package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cdb.dao.InterfaceUserDao;
import com.cdb.model.entities.QUser;
import com.cdb.model.entities.User;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDao.
 */
public class UserDao implements InterfaceUserDao {

    /** The q user. */
    private static QUser qUser = QUser.user;

    /** The session factory. */
    private SessionFactory sessionFactory;

    /**
     * Sets the session factory.
     *
     * @param sessionFactory
     *            the new session factory
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Find by user name.
     *
     * @param username
     *            the username
     * @return the user
     */
    @Transactional
    public User findByUserName(String username) {
        HibernateQueryFactory queryFactory = new HibernateQueryFactory(
                sessionFactory.openSession());
        List<User> users = new ArrayList<User>();

        users = queryFactory.select(qUser).from(qUser)
                .where(qUser.username.eq(username)).fetch();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

}
