package com.cdb.services;

import com.cdb.dto.EntrepriseDto;
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
    List<EntrepriseDto> findEntreprise();

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     */
    Optional<Entreprise> findEntrepriseById(long id);

    /**
     * Find id entreprise by name.
     *
     * @param factory
     *            the factory
     * @param entreprises
     *            the entreprises
     * @return the long
     */
    long findIdEntrepriseByName(String factory,
            List<EntrepriseDto> entreprises);

}
