package com.cdb.services;

import com.cdb.dto.PageDto;
import com.cdb.entities.Ordinateur;
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
     */
    void createOrdinateur(Ordinateur ordinateur) throws RequeteQueryException;

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @return the optional
     */
    PageDto findOrdinateurByPage(int numeroPage, int ligneParPage, String filtre);

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws RequeteQueryException 
     */
    void updateOrdinateur(Ordinateur ordinateur) throws RequeteQueryException;

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            the id
     */
    void suppressionOrdinateur(long id);

    /**
     * Count.
     *
     * @param pageActuelle
     *            the page actuelle
     * @param nbParPage
     *            the nb par page
     * @return the list
     */

}
