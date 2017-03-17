package com.cdb.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.cdb.model.entities.Ordinateur;
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
     * @param con
     *            the con
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    void createOrdinateur(Ordinateur ordinateur, Connection con)
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
    List<Ordinateur> findOrdinateurByPage(int numeroPage, int ligneParPage, String trie, boolean desc)
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
            String name, String trie, boolean desc)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    Optional<Ordinateur> findOrdinateurById(long id)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @param con
     *            the con
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    void updateOrdinateur(Ordinateur ordinateur, Connection con)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Suppression ordinateur.
     *
     * @param index
     *            the index
     * @param con
     *            the con
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    void suppressionOrdinateur(long index, Connection con)
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
