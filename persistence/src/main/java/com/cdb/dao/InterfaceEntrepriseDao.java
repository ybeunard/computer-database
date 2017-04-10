package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import com.cdb.model.entities.Company;

/**
 * The Interface InterfaceEntrepriseDao.
 */
public interface InterfaceEntrepriseDao {

    /**
     * Find entreprise.
     *
     * @return the list
     */
    List<Company> findEntreprise();

    /**
     * Find entreprise by ID.
     *
     * @param index
     *            the index
     * @return the entreprise
     */
    Optional<Company> findEntrepriseByID(long index);

}
