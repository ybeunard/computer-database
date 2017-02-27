package com.cdb.services;

import com.cdb.entities.Ordinateur;

/**
 * The Interface InterfaceGestionOrdinateur.
 */
public interface InterfaceGestionOrdinateur {

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     */
    void createOrdinateur(Ordinateur ordinateur);

    /**
     * Find ordinateur by ID.
     *
     * @param id
     *            the id
     * @return the ordinateur
     */
    Ordinateur findOrdinateurByID(long id);

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     */
    void updateOrdinateur(Ordinateur ordinateur);

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            the id
     */
    void suppressionOrdinateur(long id);

}
