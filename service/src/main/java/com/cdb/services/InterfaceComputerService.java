package com.cdb.services;

import java.util.List;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Computer;

public interface InterfaceComputerService {

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
     */
    PageDto findComputerByPage(int numPage, int rowByPage,
            String filter, String sort, boolean desc);

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the ordinateur dto
     * @throws DataAccessException
     *             the data access exception
     */
    ComputerDto findComputerById(long id);
    
    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     */
    void createComputer(Computer computer);

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     * @throws DataAccessException
     *             the data access exception
     */
    void updateComputer(Computer computer);

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            the id
     * @throws DataAccessException
     *             the data access exception
     */
    void deleteComputer(List<Long> id);
    
}
