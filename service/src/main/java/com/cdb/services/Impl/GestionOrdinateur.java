package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.model.dto.ComputerDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Computer;
import com.cdb.services.InterfaceGestionOrdinateur;
import com.cdb.utils.mappers.OrdinateurDtoMapper;
import com.cdb.utils.mappers.PageDtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Enum GestionOrdinateur.
 */
public class GestionOrdinateur implements InterfaceGestionOrdinateur {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(GestionOrdinateur.class);

    /** The ordinateur dao. */
    @Autowired
    private OrdinateurDao ordinateurDao;

    /**
     * Gets the ordinateur dao.
     *
     * @return the ordinateur dao
     */
    public OrdinateurDao getOrdinateurDao() {

        return ordinateurDao;

    }

    /**
     * Instantiates a new gestion ordinateur.
     */
    public GestionOrdinateur() {

        LOGGER.info("GestionOrdinateur instancié");

    }

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            le numero de la page
     * @param ligneParPage
     *            le nombre de ligne par page
     * @param filtre
     *            the filtre
     * @param trie
     *            the trie
     * @param desc
     *            the desc
     * @return une liste d'ordinateur
     * @throws DataAccessException
     *             the data access exception
     */
    public PageDto findOrdinateurByPage(int numeroPage, int ligneParPage,
            String filtre, String trie, boolean desc)
            throws DataAccessException {

        List<Computer> ordinateurs = new ArrayList<Computer>();
        long nombreTotal = 0;
        long pageMax = 1;
        LOGGER.info("Service : Recherche ordinateur par page");

        if (filtre == null || filtre.equals("")) {

            nombreTotal = ordinateurDao.countOrdinateur();
            LOGGER.debug("recuperation du nombre maximum d'ordinateur "
                    + nombreTotal);
            pageMax = pageMax(ligneParPage, nombreTotal);
            LOGGER.debug("recuperation du nombre maximum de page " + pageMax);
            numeroPage = verifNumPage(numeroPage, pageMax);
            LOGGER.debug(
                    "Verification du numero de page effectuer " + numeroPage);
            ordinateurs = ordinateurDao.findOrdinateurByPage(numeroPage,
                    ligneParPage, trie, desc);
            LOGGER.debug("Recuperation de la liste d'ordinateur "
                    + ordinateurs.size());

        } else {

            nombreTotal = ordinateurDao.countOrdinateurByName(filtre);
            LOGGER.debug("recuperation du nombre maximum d'ordinateur "
                    + nombreTotal);
            pageMax = pageMax(ligneParPage, nombreTotal);
            LOGGER.debug("recuperation du nombre maximum de page " + pageMax);
            numeroPage = verifNumPage(numeroPage, pageMax);
            LOGGER.debug(
                    "Verification du numero de page effectuer " + numeroPage);
            ordinateurs = ordinateurDao.findOrdinateurByName(numeroPage,
                    ligneParPage, filtre, trie, desc);
            LOGGER.debug("Recuperation de la liste d'ordinateur "
                    + ordinateurs.size());

        }

        PageDto page = PageDtoMapper.recuperationPage(ordinateurs, nombreTotal,
                numeroPage, ligneParPage, pageMax, filtre, trie, desc);
        return page;

    }

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws DataAccessException
     *             the data access exception
     */
    public ComputerDto findOrdinateurById(long id) {

        LOGGER.info("Service: Recherche d'un ordinateur par id");
        return OrdinateurDtoMapper
                    .recuperationComputerDto(ordinateurDao
                            .findOrdinateurById(id));

    }

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            à créer
     * @throws DataAccessException
     *             the data access exception
     */
    @Transactional
    public void createOrdinateur(Computer ordinateur)
            throws DataAccessException {

        LOGGER.info("Service : Creation d'un ordinateur");
        ordinateurDao.createOrdinateur(ordinateur);

    }

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            a update
     * @throws DataAccessException
     *             the data access exception
     */
    @Transactional
    public void updateOrdinateur(Computer ordinateur)
            throws DataAccessException {

        LOGGER.info("Service: Update d'un ordinateur");
        ordinateurDao.updateOrdinateur(ordinateur);

    }

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            de l'ordinateur a supprimé
     * @throws DataAccessException
     *             the data access exception
     */
    @Transactional
    public void suppressionOrdinateur(List<Long> id)
            throws DataAccessException {

        LOGGER.info("Service: Suppression d'ordinateur");

        for (long identifiant : id) {

            ordinateurDao.suppressionOrdinateur(identifiant);

        }

    }

    /**
     * Page max.
     *
     * @param nbParPage
     *            the nb par page
     * @param nombreTotal
     *            the nombre total
     * @return the int
     */
    private long pageMax(int nbParPage, long nombreTotal) {

        long pageMax = 1;

        if (nbParPage == 0) {

            LOGGER.error("Le nombre de ligne par page vaut 0, Impossible");
            pageMax = 1;

        } else {

            if (nombreTotal % nbParPage == 0) {

                pageMax = nombreTotal / nbParPage;

            } else {

                pageMax = nombreTotal / nbParPage + 1;

            }

        }

        return pageMax;

    }

    /**
     * Verif num page.
     *
     * @param numeroPage
     *            the numero page
     * @param pageMax
     *            the page max
     * @return the int
     */
    private int verifNumPage(int numeroPage, long pageMax) {

        if (numeroPage >= pageMax) {

            return (int) pageMax;

        } else if (numeroPage == 0) {

            return 1;

        } else {

            return numeroPage;

        }

    }

}
