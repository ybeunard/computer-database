package com.cdb.dao;

import java.util.List;
import com.cdb.model.entities.Company;

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
     * @param index
     *            the index
     * @return the entreprise
     */
    Company findCompanyByID(long id);
    
}
