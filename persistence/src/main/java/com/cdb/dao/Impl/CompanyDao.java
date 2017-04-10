package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdb.dao.InterfaceCompanyDao;
import com.cdb.model.entities.Company;
import com.cdb.model.entities.QCompany;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDao.
 */
public class CompanyDao implements InterfaceCompanyDao {

    /** The Constant LOGGER. */
    public final Logger LOGGER = LoggerFactory.getLogger(CompanyDao.class);

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

    /** The entreprise. */
    QCompany qCompany;

    /**
     * Instantiates a new entreprise dao.
     */
    CompanyDao() {

        qCompany = QCompany.company;
        LOGGER.info("CompanyDao Instantiated");

    }

    /**
     * Find companies.
     *
     * @return the list
     */
    @Override
    public List<Company> findCompanies() {

        LOGGER.info("Dao: search all companies");
        List<Company> companies = new ArrayList<Company>();
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        companies = query.select(qCompany).from(qCompany).fetch();
        return companies;

    }

    /**
     * Find company by ID.
     *
     * @param id
     *            the id
     * @return the company
     */
    @Override
    public Company findCompanyByID(long id) {

        LOGGER.info("Dao: search company by id");
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        Company company = query.select(qCompany).from(qCompany)
                .where(qCompany.id.eq(id)).fetchOne();
        return company;

    }

}
