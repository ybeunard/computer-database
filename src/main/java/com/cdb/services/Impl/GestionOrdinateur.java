package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.ConnexionDatabase;
import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.dto.PageDto;
import com.cdb.model.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.model.mappers.OrdinateurDtoMapper;
import com.cdb.model.mappers.PageDtoMapper;
import com.cdb.services.InterfaceGestionOrdinateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Enum GestionOrdinateur.
 */
public class GestionOrdinateur implements InterfaceGestionOrdinateur {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = LoggerFactory
            .getLogger(GestionOrdinateur.class);
    
    @Autowired
    private OrdinateurDao ordinateurDao;
    
    public OrdinateurDao getOrdinateurDao( ){
        
        return ordinateurDao;
        
     }
    
    @Autowired
    private ConnexionDatabase connexionDatabase;
    
    public ConnexionDatabase getConnexionDatabase() {
        
        return connexionDatabase;
        
    }
    
    /**
     * Instantiates a new gestion ordinateur.
     */ 
    public GestionOrdinateur(){
        
        LOGGER.info("GestionOrdinateur instancié");
        
    }

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            à créer
     * @throws RequeteQueryException
     *             if there is an issue
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     */
    @Transactional
    public void createOrdinateur(Ordinateur ordinateur)
            throws RequeteQueryException, ConnexionDatabaseException {

        LOGGER.info("Service : Creation d'un ordinateur");
        ordinateurDao.createOrdinateur(ordinateur,
                    connexionDatabase.getJdbcTemplate());

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
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public PageDto findOrdinateurByPage(int numeroPage, int ligneParPage,
            String filtre, String trie, boolean desc)
            throws ConnexionDatabaseException, RequeteQueryException {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int nombreTotal = 0;
        int pageMax = 1;
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
            ordinateurs = ordinateurDao.findOrdinateurByPage(numeroPage, ligneParPage, trie, desc);
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
            ordinateurs = ordinateurDao.findOrdinateurByName(numeroPage, ligneParPage, filtre,
                            trie, desc);
            LOGGER.debug("Recuperation de la liste d'ordinateur "
                    + ordinateurs.size());

        }

        PageDto page = PageDtoMapper.recuperationPage(ordinateurs, nombreTotal,
                numeroPage, ligneParPage, pageMax, filtre, trie, desc);
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
     *             the connexion database exception
     */
    @Transactional
    public void updateOrdinateur(Ordinateur ordinateur)
            throws RequeteQueryException, ConnexionDatabaseException {

        LOGGER.info("Service: Update d'un ordinateur");
        ordinateurDao.updateOrdinateur(ordinateur,
                    connexionDatabase.getJdbcTemplate());

    }

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            de l'ordinateur a supprimé
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    @Transactional
    public void suppressionOrdinateur(List<Long> id)
            throws ConnexionDatabaseException, RequeteQueryException {

        LOGGER.info("Service: Suppression d'ordinateur");

        for (long identifiant : id) {

            ordinateurDao.suppressionOrdinateur(identifiant, connexionDatabase.getJdbcTemplate());

        }

    }

    /**
     * Find ordinateur by id.
     *
     * @param id
     *            the id
     * @return the optional
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public OrdinateurDto findOrdinateurById(long id)
            throws ConnexionDatabaseException, EmptyResultDataAccessException {

        LOGGER.info("Service: Recherche d'un ordinateur par id");
        OrdinateurDto ordinateur = new OrdinateurDto();
        Optional<Ordinateur> ordinateurOptional = ordinateurDao.findOrdinateurById(id);

        if (ordinateurOptional.isPresent()) {

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

        if (numeroPage >= pageMax) {

            return pageMax;

        } else if (numeroPage == 0) {

            return 1;

        } else {

            return numeroPage;

        }

    }

}
