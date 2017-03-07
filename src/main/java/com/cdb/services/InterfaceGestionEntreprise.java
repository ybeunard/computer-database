package com.cdb.services;

import com.cdb.dto.EntrepriseDto;
import com.cdb.entities.Entreprise;
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
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    List<EntrepriseDto> findEntreprise() throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    Optional<Entreprise> findEntrepriseById(long id) throws ConnexionDatabaseException, RequeteQueryException;

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
