package com.cdb.services.Impl;

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
     * @param id de l'entreprise recherché
     * @return une entreprise
     */
    public Optional<Entreprise> findEntrepriseByID(long id) {

        Optional<Entreprise> entreprise = null;

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

}
