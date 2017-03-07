package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

/**
 * The Interface InterfaceOrdinateurDao.
 */
public interface InterfaceOrdinateurDao {

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
    List<Ordinateur> findOrdinateur()
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
    List<Ordinateur> findOrdinateurByPage(int numeroPage, int ligneParPage)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find ordinateur by Name.
     *
     * @param numPage
     *            the num page
     * @param nbParPage
     *            the nb par page
     * @param name
     *            le nom de l'ordinateur recherché
     * @return une liste ordinateur
     * @throws ConnexionDatabaseException
     *             if there is an issue
     * @throws RequeteQueryException
     *             if there is an issue
     */
    List<Ordinateur> findOrdinateurByName(int numPage, int nbParPage,
            String name)
            throws ConnexionDatabaseException, RequeteQueryException;
    
    Optional<Ordinateur> findOrdinateurById(long id)
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

    /**
     * Count ordinateur.
     *
     * @return the int
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    int countOrdinateur()
            throws ConnexionDatabaseException, RequeteQueryException;

}
