package com.cdb.services;

import com.cdb.entities.Entreprise;

/**
 * The Interface InterfaceGestionEntreprise.
 */
public interface InterfaceGestionEntreprise {

    /**
     * Find entreprise by ID.
     *
     * @param id
     *            the id
     * @return the entreprise
     */
    Entreprise findEntrepriseByID(long id);

}
