package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

/**
 * The Interface InstanceOrdinateurDao.
 */
public interface InstanceOrdinateurDao {

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    void createOrdinateur(Ordinateur ordinateur)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find ordinateur.
     *
     * @return the list
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    Optional<List<Optional<Ordinateur>>> findOrdinateur()
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find ordinateur by page.
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
    Optional<List<Optional<Ordinateur>>> findOrdinateurByPage(int numeroPage,
            int ligneParPage)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find ordinateur by ID.
     *
     * @param index
     *            the index
     * @return the ordinateur
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    Optional<Ordinateur> findOrdinateurByID(long index)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    void updateOrdinateur(Ordinateur ordinateur)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Suppression ordinateur.
     *
     * @param index
     *            the index
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    void suppressionOrdinateur(long index)
            throws ConnexionDatabaseException, RequeteQueryException;

}
