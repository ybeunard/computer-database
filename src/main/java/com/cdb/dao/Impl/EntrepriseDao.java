package com.cdb.dao.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.cdb.dao.InterfaceEntrepriseDao;
import com.cdb.model.entities.Entreprise;
import com.cdb.model.entities.QEntreprise;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

/**
 * The Enum EntrepriseDao.
 *
 * @author excilys
 */
public class EntrepriseDao implements InterfaceEntrepriseDao {

    /** The Constant LOGGER. */
    public final Logger LOGGER = LoggerFactory.getLogger(EntrepriseDao.class);

    /** The connexion database. */
    @Autowired
    private ConnexionDatabase connexionDatabase;

    /**
     * Gets the connexion database.
     *
     * @return the connexion database
     */
    public ConnexionDatabase getConnexionDatabase() {

        return connexionDatabase;

    }
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        LOGGER.info("session factory instancié");
    }

    /** The prop. */
    private final Properties prop = new Properties();
    
    QEntreprise entreprise;

    /**
     * Instantiates a new entreprise dao.
     */
    EntrepriseDao() {

        String file = "query.properties";

        try (InputStream stream = ConnexionDatabase.class.getClassLoader()
                .getResourceAsStream(file);) {

            prop.load(stream);

        } catch (IOException e) {

            LOGGER.error("Fichier introuvable : " + file);

        }

        entreprise = QEntreprise.entreprise;
        LOGGER.info("EntrepriseDao instancié");

    }

    /**
     * Find entreprise.
     *
     * @return une liste d'entreprise
     * @throws DataAccessException
     *             the data access exception
     */
    public List<Entreprise> findEntreprise() throws DataAccessException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();
        HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
        entreprises = query.select(entreprise).from(entreprise).fetch();
        return entreprises;

    }

    /**
     * Find entreprise by ID.
     *
     * @param index
     *            l'id de l'entreprise à rechercher
     * @return une entreprise
     * @throws DataAccessException
     *             the data access exception
     */
    public Optional<Entreprise> findEntrepriseByID(long index)
            throws DataAccessException {

        Optional<Entreprise> entreprises = Optional.empty();
        HibernateQueryFactory query = new HibernateQueryFactory(sessionFactory.openSession());
        entreprises = Optional.ofNullable(query.select(entreprise).from(entreprise).where(entreprise.id.eq(index)).fetchOne());
        return entreprises;

    }

}
