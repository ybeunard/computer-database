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
import org.springframework.jdbc.core.JdbcTemplate;

import com.cdb.dao.InterfaceEntrepriseDao;
import com.cdb.dao.Impl.mappers.EntrepriseDaoMapper;
import com.cdb.model.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

/**
 * The Enum EntrepriseDao.
 *
 * @author excilys
 */
public class EntrepriseDao implements InterfaceEntrepriseDao {

    /** The Constant LOGGER. */
    public final Logger LOGGER = LoggerFactory.getLogger(EntrepriseDao.class);
    
    @Autowired
    private ConnexionDatabase connexionDatabase;
    
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

        } catch (NullPointerException e) {

            LOGGER.error("Fichier introuvable : " + file);

        }
        
        LOGGER.info("EntrepriseDao instancié");

    }

    /**
     * Find entreprise.
     *
     * @return une liste d'entreprise
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public List<Entreprise> findEntreprise()
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Entreprise> entreprises = new ArrayList<Entreprise>();
        LOGGER.info("recherche de la liste d'entreprise");
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        entreprises = jdbcTemplate.query(prop.getProperty("QUERY_FIND_ENTREPRISES"), new EntrepriseDaoMapper());
        return entreprises;

    }

    /**
     * Find entreprise by ID.
     *
     * @param index
     *            l'id de l'entreprise à rechercher
     * @return une entreprise
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public Optional<Entreprise> findEntrepriseByID(long index)
            throws ConnexionDatabaseException, RequeteQueryException {

        Optional<Entreprise> entreprise = Optional.empty();
        LOGGER.info("recherche d'une entreprise par id: " + index);
        
        if(index <= 0){
            
            return entreprise;
            
        }
        
        JdbcTemplate jdbcTemplate = connexionDatabase.getJdbcTemplate();
        entreprise = Optional.ofNullable(jdbcTemplate.queryForObject(prop.getProperty("QUERY_FIND_ENTREPRISES_BY_ID"), new Object[]{index}, new EntrepriseDaoMapper()));
        return entreprise;

    }

}
