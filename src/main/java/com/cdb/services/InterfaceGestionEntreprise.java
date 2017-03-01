package com.cdb.services;

import com.cdb.entities.Entreprise;

import java.util.List;
import java.util.Optional;

/**
 * The Interface InterfaceGestionEntreprise.
 */
public interface InterfaceGestionEntreprise {

    /**
     * Find entreprise.
     *
     * @return the list
     */
    List<Entreprise> findEntreprise();

    /**
     * Find entreprise by ID.
     *
     * @param id
     *            the id
     * @return the entreprise
     */
    Optional<Entreprise> findEntrepriseByID(long id);

    /**
     * Find entreprise by page.
     *
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @return the optional
     */
    Optional<List<Optional<Entreprise>>> findEntrepriseByPage(int numeroPage,
            int ligneParPage);

}
