package com.cdb.services;

import com.cdb.model.dto.EntrepriseDto;
import com.cdb.model.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

import java.util.List;
import java.util.Optional;

/**
 * The Interface InterfaceGestionEntreprise.
 */
public interface InterfaceGestionEntreprise {

    /**
     * Find entreprise.
     *
     * @return the list
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    List<EntrepriseDto> findEntreprise()
            throws ConnexionDatabaseException, RequeteQueryException;

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
    Optional<Entreprise> findEntrepriseById(long id)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find id entreprise by name.
     *
     * @param factory
     *            the factory
     * @param entreprises
     *            the entreprises
     * @return the long
     */
    long findIdEntrepriseByName(String factory,
            List<EntrepriseDto> entreprises);

}
