package com.cdb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;

import com.cdb.model.entities.Entreprise;

/**
 * The Interface InterfaceEntrepriseDao.
 */
public interface InterfaceEntrepriseDao {

    /**
     * Find entreprise.
     *
     * @return the list
     * @throws DataAccessException
     *             the data access exception
     */
    List<Entreprise> findEntreprise() throws DataAccessException;

    /**
     * Find entreprise by ID.
     *
     * @param index
     *            the index
     * @return the entreprise
     * @throws DataAccessException
     *             the data access exception
     */
    Optional<Entreprise> findEntrepriseByID(long index)
            throws DataAccessException;

}
