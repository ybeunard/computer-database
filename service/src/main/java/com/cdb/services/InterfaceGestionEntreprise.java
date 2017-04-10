package com.cdb.services;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.entities.Company;

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
    List<CompanyDto> findEntreprise() throws DataAccessException;

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws DataAccessException
     *             the data access exception
     */
    Optional<Company> findEntrepriseById(long id) throws DataAccessException;

}
