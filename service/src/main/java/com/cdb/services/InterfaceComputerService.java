package com.cdb.services;

import java.util.List;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Computer;

/**
 * The Interface InterfaceComputerService.
 */
public interface InterfaceComputerService {

    /**
     * Find ordinateur by page.
     *
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @param filter
     *            the filter
     * @param sort
     *            the sort
     * @param desc
     *            the desc
     * @return the optional
     */
    PageDto findComputerByPage(PageDto page);

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the ordinateur dto
     */
    ComputerDto findComputerById(long id);

    /**
     * Creates the ordinateur.
     *
     * @param computer
     *            the computer
     */
    void createComputer(Computer computer);

    /**
     * Update ordinateur.
     *
     * @param computer
     *            the computer
     */
    void updateComputer(Computer computer);

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            the id
     */
    void deleteComputer(List<Long> id);

}
