package com.cdb.services;

import com.cdb.model.dto.EntrepriseDto;
import com.cdb.model.entities.Entreprise;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

/**
 * The Interface InterfaceGestionEntreprise.
 */
public interface InterfaceGestionEntreprise {

    /**
     * Find entreprise.
     *
     * @return the list
     * @throws DataAccessException
     *             the data access exception
     */
    List<EntrepriseDto> findEntreprise() throws DataAccessException;

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws DataAccessException
     *             the data access exception
     */
    Optional<Entreprise> findEntrepriseById(long id) throws DataAccessException;

}
