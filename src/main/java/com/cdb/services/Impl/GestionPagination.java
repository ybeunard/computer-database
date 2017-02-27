package com.cdb.services.Impl;

import java.util.List;
import com.cdb.dao.Impl.EntrepriseDao;
import com.cdb.dao.Impl.OrdinateurDao;
import com.cdb.entities.Entreprise;
import com.cdb.entities.Ordinateur;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.services.InterfaceGestionPagination;
import com.cdb.ui.UserInterpreter;

/**
 * The Class GestionPagination.
 */
public class GestionPagination implements InterfaceGestionPagination {

    /** The numero page. */
    private int numeroPage;

    /** The ligne par page. */
    private int ligneParPage;

    /** The page ordinateur. */
    private List<Ordinateur> pageOrdinateur;

    /** The page entreprise. */
    private List<Entreprise> pageEntreprise;

    /**
     * Instantiates a new gestion pagination.
     */
    public GestionPagination() {

        this.numeroPage = 1;
        this.ligneParPage = 100;

    }

    /**
     * Instantiates a new gestion pagination.
     *
     * @param ligneParPage the ligne par page
     */
    public GestionPagination(int ligneParPage) {

        this.numeroPage = 1;

        if (ligneParPage > 0) {

            this.ligneParPage = ligneParPage;

        } else {

            this.ligneParPage = 100;

        }

    }

    /**
     * @param typePage indique si la page est une page entreprise ou ordinateur
     */
    public void pagination(int typePage) {

        do {

            try {

                switch (typePage) {

                case 1:

                    pageOrdinateur = OrdinateurDao.getInstanceOrdinateurDao()
                            .findOrdinateurByPage(numeroPage, ligneParPage);

                    if (pageOrdinateur.isEmpty()) {

                        numeroPage--;
                        pageOrdinateur = OrdinateurDao
                                .getInstanceOrdinateurDao()
                                .findOrdinateurByPage(numeroPage, ligneParPage);

                    }

                    affichagePage(typePage);
                    break;

                case 2:

                    pageEntreprise = EntrepriseDao.getInstanceEntrepriseDao()
                            .findEntrepriseByPage(numeroPage, ligneParPage);

                    if (pageEntreprise.isEmpty()) {

                        numeroPage--;
                        pageEntreprise = EntrepriseDao
                                .getInstanceEntrepriseDao()
                                .findEntrepriseByPage(numeroPage, ligneParPage);

                    }

                    affichagePage(typePage);
                    break;

                default:
                    System.out.println("Type de page inconnu");
                    return;

                }

            } catch (ConnexionDatabaseException e) {

                e.printStackTrace();

            } catch (RequeteQueryException e) {

                e.printStackTrace();

            }

        } while (changementPage());

    }

    /**
     * Changement page.
     *
     * @return true, if successful
     */
    private boolean changementPage() {

        String arg = UserInterpreter.SCANNER.nextLine();

        if (arg == "b") {

            if (numeroPage > 1) {

                numeroPage--;

            }

            return true;

        } else if (arg == "n") {

            numeroPage++;
            return true;

        }

        return false;

    }

    /**
     * Affichage page.
     *
     * @param typePage
     *            the type page
     */
    private void affichagePage(int typePage) {

        switch (typePage) {

        case 1:

            for (Ordinateur ordinateur : pageOrdinateur) {

                System.out.println(ordinateur);

            }

            break;

        case 2:

            for (Entreprise entreprise : pageEntreprise) {

                System.out.println(entreprise);

            }

            break;

        default:

            System.out.println("Type de page inconnu");
            return;

        }

        System.out.println(
                "PRECENT taper b\tNEXT taper n\tEXIT taper n'importe qu'elle touche");

    }

}
