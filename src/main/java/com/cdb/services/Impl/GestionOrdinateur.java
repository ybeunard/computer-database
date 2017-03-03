package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.dto.PageDto;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
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
            .getLogger(EntrepriseDao.class);

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            à créer
     * @throws RequeteQueryException
     *             if there is an issue
     */
    public void createOrdinateur(Ordinateur ordinateur)
            throws RequeteQueryException {

        try {

            OrdinateurDao.INSTANCE_ORDINATEUR_DAO.createOrdinateur(ordinateur);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        }

    }

    /**
     * Find ordinateur by page.
     *
     * @param numeroPage
     *            le numero de la page
     * @param ligneParPage
     *            le nombre de ligne par page
     * @return une liste d'ordinateur
     */
    public PageDto findOrdinateurByPage(int numeroPage, int ligneParPage) {

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        int nombreTotal = 0;

        try {

            ordinateurs = OrdinateurDao.INSTANCE_ORDINATEUR_DAO
                    .findOrdinateurByPage(numeroPage, ligneParPage);
            LOGGER.info("Recuperation de la liste d'ordinateur " + ordinateurs.size());
            nombreTotal = OrdinateurDao.INSTANCE_ORDINATEUR_DAO
                    .countOrdinateur();
            LOGGER.info("recuperation du nombre maximum d'ordinateur " + nombreTotal);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

        PageDto page = PageDtoMapper.INSTANCE_PAGE_DTO_MAPPER.recuperationPage(
                ordinateurs, nombreTotal, numeroPage, ligneParPage);
        return page;

    }

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            a update
     */
    public void updateOrdinateur(Ordinateur ordinateur) {

        try {

            OrdinateurDao.INSTANCE_ORDINATEUR_DAO.updateOrdinateur(ordinateur);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

    }

    /**
     * Suppression ordinateur.
     *
     * @param id
     *            de l'ordinateur a supprimé
     */
    public void suppressionOrdinateur(long id) {

        try {

            OrdinateurDao.INSTANCE_ORDINATEUR_DAO.suppressionOrdinateur(id);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

    }

}
