package com.cdb.services;

import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Ordinateur;
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
     * @return the optional
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    PageDto findOrdinateurByPage(int numeroPage, int ligneParPage,
            String filtre) throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws RequeteQueryException
     *             the requete query exception
     * @throws ConnexionDatabaseException 
     */
    void updateOrdinateur(Ordinateur ordinateur) throws RequeteQueryException, ConnexionDatabaseException;

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            the id
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    void suppressionOrdinateur(long id) throws ConnexionDatabaseException, RequeteQueryException;

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the ordinateur dto
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    OrdinateurDto findOrdinateurById(long id) throws ConnexionDatabaseException, RequeteQueryException;

}
