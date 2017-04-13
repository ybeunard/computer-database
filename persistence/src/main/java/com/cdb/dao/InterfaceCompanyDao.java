package com.cdb.dao;

import java.util.List;
import com.cdb.model.entities.Company;

/**
 * The Interface InterfaceCompanyDao.
 */
public interface InterfaceCompanyDao {

    /**
     * Find entreprise.
     *
     * @return the list
     */
    List<Company> findCompanies();
    
    /**
     * Find ordinateur by page.
     *
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @return the list
     */
    List<Company> findCompanyByPage(int numPage, int rowByPage);

    /**
     * Find ordinateur by Name.
     *
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @param name
     *            le nom de l'ordinateur recherché
     * @return une liste ordinateur
     */
    List<Company> findCompanyByName(int numPage, int rowByPage, String name);

    /**
     * Find entreprise by ID.
     *
     * @param id
     *            the id
     * @return the entreprise
     */
    Company findCompanyByID(long id);
    
    /**
     * Suppression ordinateur.
     *
     * @param id
     *            the id
     */
    void deleteCompany(long id);

    /**
     * Count ordinateur.
     *
     * @return the int
     */
    long countCompany();

    /**
     * Count computer by name.
     *
     * @param filter
     *            the filter
     * @return the long
     */
    long countCompanyByName(String filter);

}
