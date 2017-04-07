package com.cdb.dao;

import java.util.List;
import com.cdb.model.entities.Ordinateur;

/**
 * The Interface InterfaceOrdinateurDao.
 */
public interface InterfaceOrdinateurDao {

    /**
     * Find ordinateur.
     *
     * @return the list
     */
    List<Ordinateur> findOrdinateur();

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
    List<Ordinateur> findOrdinateurByPage(int numeroPage, int ligneParPage,
            String trie, boolean desc);

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
    List<Ordinateur> findOrdinateurByName(int numPage, int nbParPage,
            String name, String trie, boolean desc);

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the optional
     */
    Ordinateur findOrdinateurById(long id);

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     */
    void createOrdinateur(Ordinateur ordinateur);

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            the ordinateur
     */
    void updateOrdinateur(Ordinateur ordinateur);

    /**
     * Suppression ordinateur.
     *
     * @param index
     *            the index
     */
    void suppressionOrdinateur(long index);

    /**
     * Count ordinateur.
     *
     * @return the int
     */
    long countOrdinateur();

}
