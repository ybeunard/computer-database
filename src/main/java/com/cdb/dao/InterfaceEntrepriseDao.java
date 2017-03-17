package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import com.cdb.model.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

/**
 * The Interface InterfaceEntrepriseDao.
 */
public interface InterfaceEntrepriseDao {

    /**
     * Find entreprise.
     *
     * @return the list
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    List<Entreprise> findEntreprise()
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find entreprise by ID.
     *
     * @param index
     *            the index
     * @return the entreprise
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    Optional<Entreprise> findEntrepriseByID(long index)
            throws ConnexionDatabaseException, RequeteQueryException;

}
