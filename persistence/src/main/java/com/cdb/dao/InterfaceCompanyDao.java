package com.cdb.dao;

import java.util.List;
import com.cdb.model.entities.Company;

// TODO: Auto-generated Javadoc
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
     * Find entreprise by ID.
     *
     * @param id
     *            the id
     * @return the entreprise
     */
    Company findCompanyByID(long id);

}
