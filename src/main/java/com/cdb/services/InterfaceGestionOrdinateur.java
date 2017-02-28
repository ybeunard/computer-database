package com.cdb.services;

import com.cdb.DTO.OrdinateurDTO;
import com.cdb.entities.Ordinateur;

import java.util.List;
import java.util.Optional;

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
    Optional<Ordinateur> findOrdinateurByID(long id);

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @return the optional
     */
    List<OrdinateurDTO> findOrdinateurByPage(int numeroPage, int ligneParPage);

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
