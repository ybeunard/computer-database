package com.cdb.services.Impl;

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
     * @param ordinateur à créer
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
     * @param id de l'ordinateur recherché
     * @return un ordinateur
     */
    public Ordinateur findOrdinateurByID(long id) {

        Ordinateur ordinateur = null;

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
     * @param ordinateur a update
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
     * @param id de l'ordinateur a supprimé
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
