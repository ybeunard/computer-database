package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import com.cdb.model.entities.Entreprise;

/**
 * The Interface InterfaceEntrepriseDao.
 */
public interface InterfaceEntrepriseDao {

    /**
     * Find entreprise.
     *
     * @return the list
     */
    List<Entreprise> findEntreprise();

    /**
     * Find entreprise by ID.
     *
     * @param index
     *            the index
     * @return the entreprise
     */
    Optional<Entreprise> findEntrepriseByID(long index);

}
