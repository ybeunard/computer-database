package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import com.cdb.entities.Entreprise;
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
    List<Optional<Entreprise>> findEntreprise()
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find entreprise by page.
     *
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @return the list
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    List<Optional<Entreprise>> findEntrepriseByPage(int numeroPage,
            int ligneParPage)
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

    /**
     * Find entreprise by name.
     *
     * @param name
     *            the name
     * @return the optional
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    Optional<Entreprise> findEntrepriseByName(String name)
            throws ConnexionDatabaseException, RequeteQueryException;

}
