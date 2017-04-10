package com.cdb.dao;

import java.util.List;

import com.cdb.model.entities.Computer;

public interface InterfaceComputerDao {

    /**
     * Find ordinateur.
     *
     * @return the list
     */
    List<Computer> findComputers();

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            the numero page
     * @param ligneParPage
     *            the ligne par page
     * @param trie
     *            the trie
     * @param desc
     *            the desc
     * @return the list
     */
    List<Computer> findComputerByPage(int numPage, int rowByPage,
            String sort, boolean desc);

    /**
     * Find ordinateur by Name.
     *
     * @param numPage
     *            the num page
     * @param nbParPage
     *            the nb par page
     * @param name
     *            le nom de l'ordinateur recherché
     * @param trie
     *            the trie
     * @param desc
     *            the desc
     * @return une liste ordinateur
     */
    List<Computer> findComputerByName(int numPage, int rowByPage,
            String name, String sort, boolean desc);

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
     * @param ordinateur
     *            the ordinateur
     */
    void createComputer(Computer computer);

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     */
    void updateComputer(Computer computer);

    /**
     * Suppression ordinateur.
     *
     * @param index
     *            the index
     */
    void deleteComputer(long id);

    /**
     * Count ordinateur.
     *
     * @return the int
     */
    long countComputer();
    
    public long countComputerByName(String filter);
    
}
