package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.dto.OrdinateurDto;
import com.cdb.dto.PageDto;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.mappers.OrdinateurDtoMapper;
import com.cdb.mappers.PageDtoMapper;
import com.cdb.services.InterfaceGestionOrdinateur;

/**
 * The Enum GestionOrdinateur.
 */
public enum GestionOrdinateur implements InterfaceGestionOrdinateur {

    /** The instance gestion ordinateur. */
    INSTANCE_GESTION_ORDINATEUR;

    /**
     * Instantiates a new gestion ordinateur.
     */
    GestionOrdinateur() {

    }

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(GestionOrdinateur.class);

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            à créer
     * @throws RequeteQueryException
     *             if there is an issue
     * @throws ConnexionDatabaseException 
     */
    public void createOrdinateur(Ordinateur ordinateur)
            throws RequeteQueryException, ConnexionDatabaseException {

        OrdinateurDao.INSTANCE_ORDINATEUR_DAO.createOrdinateur(ordinateur);

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
     * @return une liste d'ordinateur
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    public PageDto findOrdinateurByPage(int numeroPage, int ligneParPage,
            String filtre) throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int nombreTotal = 0;
        int pageMax = 1;

        if (filtre == null || filtre.equals("")) {

            nombreTotal = OrdinateurDao.INSTANCE_ORDINATEUR_DAO
                    .countOrdinateur();
            LOGGER.info("recuperation du nombre maximum d'ordinateur "
                    + nombreTotal);
            pageMax = pageMax(ligneParPage, nombreTotal);
            LOGGER.info(
                    "recuperation du nombre maximum de page " + pageMax);
            numeroPage = verifNumPage(numeroPage, pageMax);
            LOGGER.info("Verification du numero de page effectuer "
                    + numeroPage);
            ordinateurs = OrdinateurDao.INSTANCE_ORDINATEUR_DAO
                    .findOrdinateurByPage(numeroPage, ligneParPage);
            LOGGER.info("Recuperation de la liste d'ordinateur "
                    + ordinateurs.size());

        } else {

            nombreTotal = OrdinateurDao.INSTANCE_ORDINATEUR_DAO
                    .countOrdinateurByName(filtre);
            LOGGER.info("recuperation du nombre maximum d'ordinateur "
                    + nombreTotal);
            pageMax = pageMax(ligneParPage, nombreTotal);
            LOGGER.info(
                    "recuperation du nombre maximum de page " + pageMax);
            numeroPage = verifNumPage(numeroPage, pageMax);
            LOGGER.info("Verification du numero de page effectuer "
                    + numeroPage);
            ordinateurs = OrdinateurDao.INSTANCE_ORDINATEUR_DAO
                    .findOrdinateurByName(numeroPage, ligneParPage, filtre);
            LOGGER.info("Recuperation de la liste d'ordinateur "
                    + ordinateurs.size());

        }

        PageDto page = PageDtoMapper.recuperationPage(
                ordinateurs, nombreTotal, numeroPage, ligneParPage, pageMax,
                filtre);
        return page;

    }

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            a update
     * @throws RequeteQueryException
     *             the requete query exception
     * @throws ConnexionDatabaseException 
     */
    public void updateOrdinateur(Ordinateur ordinateur)
            throws RequeteQueryException, ConnexionDatabaseException {

        OrdinateurDao.INSTANCE_ORDINATEUR_DAO.updateOrdinateur(ordinateur);

    }

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            de l'ordinateur a supprimé
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    public void suppressionOrdinateur(long id) throws ConnexionDatabaseException, RequeteQueryException {

        OrdinateurDao.INSTANCE_ORDINATEUR_DAO.suppressionOrdinateur(id);

    }

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    public OrdinateurDto findOrdinateurById(long id) throws ConnexionDatabaseException, RequeteQueryException {

        OrdinateurDto ordinateur = null;
        Optional<Ordinateur> ordinateurOptional = OrdinateurDao.INSTANCE_ORDINATEUR_DAO
                    .findOrdinateurById(id);
        
        if(ordinateurOptional.isPresent()) {
            
            ordinateur = OrdinateurDtoMapper
                    .recuperationOrdinateurDto(ordinateurOptional.get());
        
        }
        
        return ordinateur;

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
    private int pageMax(int nbParPage, int nombreTotal) {

        int pageMax = 1;

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
    private int verifNumPage(int numeroPage, int pageMax) {

        if (numeroPage > pageMax) {

            return pageMax;

        } else {

            return numeroPage;

        }

    }

}
