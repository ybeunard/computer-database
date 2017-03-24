package com.cdb.services;

import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Ordinateur;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * The Interface InterfaceGestionOrdinateur.
 */
public interface InterfaceGestionOrdinateur {

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws DataAccessException
     *             the data access exception
     */
    void createOrdinateur(Ordinateur ordinateur) throws DataAccessException;

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @param filtre
     *            the filtre
     * @param trie
     *            the trie
     * @param desc
     *            the desc
     * @return the optional
     * @throws DataAccessException
     *             the data access exception
     */
    PageDto findOrdinateurByPage(int numeroPage, int ligneParPage,
            String filtre, String trie, boolean desc)
            throws DataAccessException;

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws DataAccessException
     *             the data access exception
     */
    void updateOrdinateur(Ordinateur ordinateur) throws DataAccessException;

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            the id
     * @throws DataAccessException
     *             the data access exception
     */
    void suppressionOrdinateur(List<Long> id) throws DataAccessException;

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the ordinateur dto
     * @throws DataAccessException
     *             the data access exception
     */
    OrdinateurDto findOrdinateurById(long id) throws DataAccessException;

}
