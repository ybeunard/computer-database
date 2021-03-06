package com.cdb.services;

import java.util.List;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.entities.Company;

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
     * Find ordinateur by page.
     *
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @param filter
     *            the filter
     * @return the optional
     */
    List<CompanyDto> findCompanyByPage(int numPage, int rowByPage,
            String filter);

    /**
     * Find entreprise by id.
     *
     * @param id
     *            the id
     * @return the optional
     */
    CompanyDto findCompanyById(long id);

    /**
     * Delete one company.
     *
     * @param id
     *            the id
     */
    void deleteOneCompany(Long id);

    /**
     * Find company by filter.
     *
     * @param name
     *            : name
     * @return the list
     */
    List<Company> findCompanyByFilter(String name);

}
