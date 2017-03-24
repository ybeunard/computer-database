package com.cdb.dao.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cdb.dao.InterfaceEntrepriseDao;
import com.cdb.dao.Impl.mappers.EntrepriseDaoMapper;
import com.cdb.model.entities.Entreprise;

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

    /** The prop. */
    private final Properties prop = new Properties();

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
        LOGGER.info("Dao: recherche de la liste d'entreprise");
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        entreprises = jdbcTemplate.query(
                prop.getProperty("QUERY_FIND_ENTREPRISES"),
                new EntrepriseDaoMapper());
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

        Optional<Entreprise> entreprise = Optional.empty();
        LOGGER.info("Dao: recherche d'une entreprise par id: " + index);

        if (index <= 0) {

            return entreprise;

        }

        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        entreprise = Optional.ofNullable(jdbcTemplate.queryForObject(
                prop.getProperty("QUERY_FIND_ENTREPRISES_BY_ID"),
                new Object[] {index}, new EntrepriseDaoMapper()));
        return entreprise;

    }

}
