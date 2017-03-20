package com.cdb.services;

import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Ordinateur;

import java.util.List;

import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;

/**
 * The Interface InterfaceGestionOrdinateur.
 */
public interface InterfaceGestionOrdinateur {

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws RequeteQueryException
     *             the requete query exception
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     */
    void createOrdinateur(Ordinateur ordinateur)
            throws RequeteQueryException, ConnexionDatabaseException;

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @param filtre
     *            the filtre
     * @param trie
     *            the trie
     * @param desc
     *            the desc
     * @return the optional
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    PageDto findOrdinateurByPage(int numeroPage, int ligneParPage,
            String filtre, String trie, boolean desc)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws RequeteQueryException
     *             the requete query exception
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     */
    void updateOrdinateur(Ordinateur ordinateur)
            throws RequeteQueryException, ConnexionDatabaseException;

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            the id
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    void suppressionOrdinateur(List<Long> id)
            throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the ordinateur dto
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    OrdinateurDto findOrdinateurById(long id)
            throws ConnexionDatabaseException, RequeteQueryException;

}
