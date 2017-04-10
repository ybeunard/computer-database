package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdb.dao.InterfaceUserDao;
import com.cdb.model.entities.QUser;
import com.cdb.model.entities.User;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

public class UserDao implements InterfaceUserDao {

    /** The Constant logger. */
    public final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    
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
        LOGGER.info("session factory Instantiated");
        
    }

    /** The ordinateur. */
    QUser qUser;
    
    UserDao() {
        
        qUser = QUser.user;
        
    }
    
    @Override
    public User findByUserName(String username) {
        
        LOGGER.info("Dao: search all user");
        List<User> users = new ArrayList<User>();
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        users = query.select(qUser).from(qUser).where(qUser.username.like("%" + username + "%")).fetch();

        if (users.size() > 0) {
            
            return users.get(0);
            
        } else {
            
            return null;
            
        }
        
    }

}
