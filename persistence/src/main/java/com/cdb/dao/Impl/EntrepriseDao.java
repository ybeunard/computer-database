package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdb.dao.InterfaceEntrepriseDao;
import com.cdb.model.entities.Company;
import com.cdb.model.entities.QCompany;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

// TODO: Auto-generated Javadoc
/**
 * The Enum EntrepriseDao.
 *
 * @author excilys
 */
public class EntrepriseDao implements InterfaceEntrepriseDao {

    /** The Constant LOGGER. */
    public final Logger LOGGER = LoggerFactory.getLogger(EntrepriseDao.class);

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
        LOGGER.info("session factory instancié");
    }

    /** The entreprise. */
    QCompany entreprise;

    /**
     * Instantiates a new entreprise dao.
     */
    EntrepriseDao() {

        entreprise = QCompany.company;
        LOGGER.info("EntrepriseDao instancié");

    }

    /**
     * Find entreprise.
     *
     * @return une liste d'entreprise
     */
    public List<Company> findEntreprise() {

        List<Company> entreprises = new ArrayList<Company>();
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        entreprises = query.select(entreprise).from(entreprise).fetch();
        return entreprises;

    }

    /**
     * Find entreprise by ID.
     *
     * @param index
     *            l'id de l'entreprise à rechercher
     * @return une entreprise
     */
    public Optional<Company> findEntrepriseByID(long index) {

        Optional<Company> entreprises = Optional.empty();
        HibernateQueryFactory query = new HibernateQueryFactory(
                sessionFactory.openSession());
        entreprises = Optional.ofNullable(query.select(entreprise)
                .from(entreprise).where(entreprise.id.eq(index)).fetchOne());
        return entreprises;

    }

}
