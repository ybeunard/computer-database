package com.cdb.services;

import java.util.List;

import com.cdb.model.dto.CompanyDto;

/**
 * The Interface InterfaceCompanyService.
 */
public interface InterfaceCompanyService {

    /**
     * Find entreprise.
     *
     * @return the list
     */
    List<CompanyDto> findCompanies();

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     */
    CompanyDto findCompanyById(long id);

}
