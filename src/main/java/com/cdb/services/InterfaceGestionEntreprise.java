package com.cdb.services;

import com.cdb.entities.Entreprise;
import java.util.Optional;

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
    Optional<Entreprise> findEntrepriseByID(long id);

}
