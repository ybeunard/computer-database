package com.cdb.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.dto.OrdinateurDto;
import com.cdb.dto.OrdinateurDto.OrdinateurDtoBuilder;
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

            OrdinateurDao.getInstanceOrdinateurDao()
                    .createOrdinateur(ordinateur);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        }

    }

    /**
     * Find ordinateur by Name.
     *
     * @param name
     *            de l'ordinateur recherché
     * @return une liste d'ordinateur
     */
    public List<OrdinateurDto> findOrdinateurByName(String name) {

        Optional<List<Optional<Ordinateur>>> ordinateursOptional = Optional
                .empty();
        List<OrdinateurDto> ordinateurs = new ArrayList<OrdinateurDto>();
        OrdinateurDtoBuilder ordinateur;

        try {

            ordinateursOptional = OrdinateurDao.getInstanceOrdinateurDao()
                    .findOrdinateurByName(name);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }
        if (ordinateursOptional.isPresent()) {

            for (Optional<Ordinateur> c : ordinateursOptional.get()) {

                if (c.isPresent()) {

                    ordinateur = new OrdinateurDtoBuilder(c.get().getName())
                            .dateInterrompu(c.get().getDateInterrompu())
                            .dateIntroduit(c.get().getDateIntroduit());

                    if (c.get().getFabricant().isPresent()) {

                        ordinateur.factory(
                                c.get().getFabricant().get().getName());

                    }

                    ordinateurs.add(ordinateur.build());

                }

            }

        }

        return ordinateurs;

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
    public List<OrdinateurDto> findOrdinateurByPage(int numeroPage,
            int ligneParPage) {

        Optional<List<Optional<Ordinateur>>> ordinateursOptional = Optional
                .empty();
        List<OrdinateurDto> ordinateurs = new ArrayList<OrdinateurDto>();
        OrdinateurDtoBuilder ordinateur;

        try {

            ordinateursOptional = OrdinateurDao.getInstanceOrdinateurDao()
                    .findOrdinateurByPage(numeroPage, ligneParPage);

        } catch (ConnexionDatabaseException e) {

            e.printStackTrace();

        } catch (RequeteQueryException e) {

            e.printStackTrace();

        }

        if (ordinateursOptional.isPresent()) {

            for (Optional<Ordinateur> c : ordinateursOptional.get()) {

                if (c.isPresent()) {

                    ordinateur = new OrdinateurDtoBuilder(c.get().getName())
                            .dateInterrompu(c.get().getDateInterrompu())
                            .dateIntroduit(c.get().getDateIntroduit());

                    if (c.get().getFabricant().isPresent()) {

                        ordinateur.factory(
                                c.get().getFabricant().get().getName());

                    }

                    ordinateurs.add(ordinateur.build());

                }

            }

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

    /**
     * Count.
     *
     * @param pageActuelle
     *            la page courante
     * @param nbParPage
     *            le nombre de ligne par page
     * @return une liste d'entier
     */
    public List<Integer> count(int pageActuelle, int nbParPage) {

        List<Integer> entiers = new ArrayList<Integer>();
        int nombreTotal = 0;

        try {

            nombreTotal = OrdinateurDao.getInstanceOrdinateurDao()
                    .countOrdinateur();

        } catch (ConnexionDatabaseException | RequeteQueryException e) {

            e.printStackTrace();

        }

        if (nbParPage == 0) {

            LOGGER.error("Le nombre de ligne par page vaut 0, Impossible");
            return entiers;

        }

        if (nombreTotal % nbParPage == 0) {

            nombreTotal = nombreTotal / nbParPage;

        } else {

            nombreTotal = nombreTotal / nbParPage + 1;

        }

        if (nombreTotal > 7 && pageActuelle > nombreTotal - 4) {

            for (int i = nombreTotal - 6; i < nombreTotal + 1; i++) {

                entiers.add(i);

            }

        } else if (nombreTotal > 7 && pageActuelle > 4) {

            for (int i = pageActuelle - 3; i < pageActuelle + 4; i++) {

                entiers.add(i);

            }

        } else if (nombreTotal > 7) {

            for (int i = 1; i < 8; i++) {

                entiers.add(i);

            }

        } else {

            for (int i = 1; i < nombreTotal + 1; i++) {

                entiers.add(i);

            }

        }

        return entiers;

    }

    /**
     * Page max.
     *
     * @param nbParPage
     *            nombre de ligne par page
     * @return le nombre de page max
     */
    public int pageMax(int nbParPage) {

        int nombreTotal = 0;

        try {

            nombreTotal = OrdinateurDao.getInstanceOrdinateurDao()
                    .countOrdinateur();

        } catch (ConnexionDatabaseException | RequeteQueryException e) {

            e.printStackTrace();

        }

        if (nbParPage == 0) {

            LOGGER.error("Le nombre de ligne par page vaut 0, Impossible");
            return 1;

        }

        if (nombreTotal % nbParPage == 0) {

            nombreTotal = nombreTotal / nbParPage;

        } else {

            nombreTotal = nombreTotal / nbParPage + 1;

        }

        return nombreTotal;

    }

    /**
     * @return le nombre max d'ordinateur
     */
    public int countMax() {

        int nombreTotal = 0;

        try {

            nombreTotal = OrdinateurDao.getInstanceOrdinateurDao()
                    .countOrdinateur();

        } catch (ConnexionDatabaseException | RequeteQueryException e) {

            e.printStackTrace();

        }

        return nombreTotal;

    }

}
