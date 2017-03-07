package com.cdb.ui;

import java.util.List;
import com.cdb.dto.EntrepriseDto;
import com.cdb.dto.OrdinateurDto;
import com.cdb.dto.PageDto;
import com.cdb.exception.ConnexionDatabaseException;
import com.cdb.exception.RequeteQueryException;
import com.cdb.services.Impl.GestionEntreprise;
import com.cdb.services.Impl.GestionOrdinateur;

/**
 * The Class GestionPagination.
 */
public class GestionPagination {

    /** The numero page. */
    private int numeroPage;

    /** The ligne par page. */
    private int ligneParPage;

    /** The page ordinateur. */
    private PageDto pageOrdinateur;

    /** The page entreprise. */
    private List<EntrepriseDto> pageEntreprise;

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
     * @param ligneParPage
     *            the ligne par page
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
     * @param typePage
     *            indique si la page est une page entreprise ou ordinateur
     * @throws RequeteQueryException 
     * @throws ConnexionDatabaseException 
     */
    public void pagination(int typePage) throws ConnexionDatabaseException, RequeteQueryException {

        do {

            switch (typePage) {

            case 1:

                pageOrdinateur = GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                        .findOrdinateurByPage(numeroPage, ligneParPage, "");

                if (pageOrdinateur.getContenue().isEmpty()) {

                    numeroPage--;
                    pageOrdinateur = GestionOrdinateur.INSTANCE_GESTION_ORDINATEUR
                            .findOrdinateurByPage(numeroPage, ligneParPage, "");

                }

                affichagePage(typePage);
                break;

            case 2:

                pageEntreprise = GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                        .findEntreprise();

                if (pageEntreprise.isEmpty()) {

                    numeroPage--;
                    pageEntreprise = GestionEntreprise.INSTANCE_GESTION_ENTREPRISE
                            .findEntreprise();

                }

                affichagePage(typePage);
                break;

            default:
                System.out.println("Type de page inconnu");
                return;

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

            for (OrdinateurDto ordinateur : pageOrdinateur.getContenue()) {

                System.out.println(ordinateur);

            }

            break;

        case 2:

            for (EntrepriseDto entreprise : pageEntreprise) {

                System.out.println(entreprise);

            }

            break;

        default:

            System.out.println("Type de page inconnu");
            return;

        }

        System.out.println(
                "PRECEDENT taper b\tNEXT taper n\tEXIT taper n'importe qu'elle touche");

    }

}
