package com.cdb.services;

import java.util.List;

import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Interface InterfaceComputerService.
 */
public interface InterfaceComputerService {

    /**
     * Find ordinateur by page.
     *
     * @param page the page
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

    /**
     * Suppression ordinateur by one id.
     *
     * @param id
     *            the id
     */
    void deleteOneComputer(Long id);

}
