package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.model.dto.EntrepriseDto;
import com.cdb.model.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.services.InterfaceGestionEntreprise;
import com.cdb.utils.mappers.EntrepriseDtoMapper;

/**
 * The Enum GestionEntreprise.
 */
@Component
public class GestionEntreprise implements InterfaceGestionEntreprise {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(GestionEntreprise.class);
    
    @Autowired
    private EntrepriseDao entrepriseDao;
    
    public EntrepriseDao getEntrepriseDao() {
        
        return entrepriseDao;
        
    }
    
    /**
     * Instantiates a new gestion entreprise.
     */
    GestionEntreprise() {

        LOGGER.info("GestionEntreprise instancié");
        
    }
    
    /**
     * Find entreprise.
     *
     * @return the list
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public List<EntrepriseDto> findEntreprise()
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("Recherche de toutes les entreprises");
        List<Entreprise> entreprises = new ArrayList<Entreprise>();
        entreprises = entrepriseDao.findEntreprise();
        return EntrepriseDtoMapper.recuperationListEntreprise(entreprises);

    }

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public Optional<Entreprise> findEntrepriseById(long id)
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("Service: Recherche d'une entreprise par id");
        return entrepriseDao.findEntrepriseByID(id);

    }

    /**
     * Find entreprise by Name.
     *
     * @param factory
     *            the name factory
     * @param entreprises
     *            the list entreprise to compare
     * @return the id
     */
    public long findIdEntrepriseByName(String factory,
            List<EntrepriseDto> entreprises) {

        LOGGER.info("Service: Recherche d'une entreprise par nom");
        long id = 0;

        for (EntrepriseDto entreprise : entreprises) {

            if (entreprise.getName().equals(factory)) {

                id = entreprise.getId();
                break;

            }

        }

        return id;

    }

}
