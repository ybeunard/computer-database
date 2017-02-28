package com.cdb.services.Impl;

import java.util.List;
import java.util.Optional;

import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
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

    /**
     * Gets the instance gestion ordinateur.
     *
     * @return the instance gestion ordinateur
     */
    public static final GestionOrdinateur getInstanceGestionOrdinateur() {

        return INSTANCE_GESTION_ORDINATEUR;

    }

    /**
     * Creates the ordinateur.
     *
     * @param ordinateur
     *            à créer
     */
    public void createOrdinateur(Ordinateur ordinateur) {

        try {

            OrdinateurDao.getInstanceOrdinateurDao()
                    .createOrdinateur(ordinateur);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

    }

    /**
     * Find ordinateur by ID.
     *
     * @param id
     *            de l'ordinateur recherché
     * @return un ordinateur
     */
    public Optional<Ordinateur> findOrdinateurByID(long id) {

        Optional<Ordinateur> ordinateur = Optional.empty();

        try {

            ordinateur = OrdinateurDao.getInstanceOrdinateurDao()
                    .findOrdinateurByID(id);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

        return ordinateur;

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
    public Optional<List<Optional<Ordinateur>>> findOrdinateurByPage(
            int numeroPage, int ligneParPage) {

        Optional<List<Optional<Ordinateur>>> ordinateurs = Optional.empty();

        try {

            ordinateurs = OrdinateurDao.getInstanceOrdinateurDao()
                    .findOrdinateurByPage(numeroPage, ligneParPage);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

        return ordinateurs;

    }

    /**
     * Update ordinateur.
     *
     * @param ordinateur
     *            a update
     */
    public void updateOrdinateur(Ordinateur ordinateur) {

        try {

            OrdinateurDao.getInstanceOrdinateurDao()
                    .updateOrdinateur(ordinateur);

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

            OrdinateurDao.getInstanceOrdinateurDao().suppressionOrdinateur(id);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

    }

}
