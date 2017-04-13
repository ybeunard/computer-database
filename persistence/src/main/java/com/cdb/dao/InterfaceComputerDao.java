package com.cdb.dao;

import java.util.List;

import com.cdb.model.entities.Computer;

/**
 * The Interface InterfaceComputerDao.
 */
public interface InterfaceComputerDao {

    /**
     * Find ordinateur by page.
     *
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @param sort
     *            the sort
     * @param desc
     *            the desc
     * @return the list
     */
    List<Computer> findComputerByPage(int numPage, int rowByPage, String sort,
            boolean desc);

    /**
     * Find ordinateur by Name.
     *
     * @param numPage
     *            the num page
     * @param rowByPage
     *            the row by page
     * @param name
     *            le nom de l'ordinateur recherché
     * @param sort
     *            the sort
     * @param desc
     *            the desc
     * @return une liste ordinateur
     */
    List<Computer> findComputerByName(int numPage, int rowByPage, String name,
            String sort, boolean desc);

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the optional
     */
    Computer findComputerById(long id);

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
    void deleteComputer(long id);

    /**
     * Count ordinateur.
     *
     * @return the int
     */
    long countComputer();

    /**
     * Count computer by name.
     *
     * @param filter
     *            the filter
     * @return the long
     */
    long countComputerByName(String filter);

}
