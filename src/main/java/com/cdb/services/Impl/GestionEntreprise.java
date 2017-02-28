package com.cdb.services.Impl;

import java.util.List;
import java.util.Optional;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.entities.Entreprise;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.services.InterfaceGestionEntreprise;

/**
 * The Enum GestionEntreprise.
 */
public enum GestionEntreprise implements InterfaceGestionEntreprise {

    /** The instance gestion entreprise. */
    INSTANCE_GESTION_ENTREPRISE;

    /**
     * Instantiates a new gestion entreprise.
     */
    GestionEntreprise() {

    }

    /**
     * Gets the instance gestion entreprise.
     *
     * @return the instance gestion entreprise
     */
    public static final GestionEntreprise getInstanceGestionEntreprise() {

        return INSTANCE_GESTION_ENTREPRISE;

    }

    /**
     * Find entreprise by ID.
     *
     * @param id
     *            de l'entreprise recherché
     * @return une entreprise
     */
    public Optional<Entreprise> findEntrepriseByID(long id) {

        Optional<Entreprise> entreprise = Optional.empty();

        try {

            entreprise = EntrepriseDao.getInstanceEntrepriseDao()
                    .findEntrepriseByID(id);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

        return entreprise;

    }

    /**
     * Find entreprise by page.
     *
     * @param numeroPage
     *            le numero de page
     * @param ligneParPage
     *            le nombre de ligne par page
     * @return une liste d'entreprise
     */
    public Optional<List<Optional<Entreprise>>> findEntrepriseByPage(
            int numeroPage, int ligneParPage) {

        Optional<List<Optional<Entreprise>>> entreprises = Optional.empty();

        try {

            entreprises = EntrepriseDao.getInstanceEntrepriseDao()
                    .findEntrepriseByPage(numeroPage, ligneParPage);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

        return entreprises;

    }

}
