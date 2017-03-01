package com.cdb.services;

import com.cdb.DTO.OrdinateurDTO;
import com.cdb.entities.Ordinateur;

import java.util.List;

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
     * Find ordinateur by Name.
     *
     * @param name
     *            de l'ordinateur recherché
     * @return the list ordinateur
     */
    List<OrdinateurDTO> findOrdinateurByName(String name);

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

    /**
     * Count.
     *
     * @param pageActuelle
     *            the page actuelle
     * @param nbParPage
     *            the nb par page
     * @return the list
     */
    List<Integer> count(int pageActuelle, int nbParPage);

    /**
     * Page max.
     *
     * @param nbParPage
     *            the nb par page
     * @return the int
     */
    int pageMax(int nbParPage);

    /**
     * Count max.
     *
     * @return the int
     */
    int countMax();

}
