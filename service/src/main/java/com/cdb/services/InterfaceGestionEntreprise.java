package com.cdb.services;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.entities.Company;

import java.util.List;

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
    Company findEntrepriseById(long id) throws DataAccessException;

}
