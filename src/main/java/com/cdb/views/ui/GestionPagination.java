package com.cdb.views.ui;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cdb.model.dto.EntrepriseDto;
import com.cdb.model.dto.OrdinateurDto;
import com.cdb.model.dto.PageDto;
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

    private ApplicationContext context = new ClassPathXmlApplicationContext(
            "springConfig.xml");
    private GestionOrdinateur gestionOrdinateur = (GestionOrdinateur) context
            .getBean("GestionOrdinateur");
    private GestionEntreprise gestionEntreprise = (GestionEntreprise) context
            .getBean("GestionEntreprise");

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
     * Pagination.
     *
     * @param typePage
     *            indique si la page est une page entreprise ou ordinateur
     * @throws ConnexionDatabaseException
     *             the connexion database exception
     * @throws RequeteQueryException
     *             the requete query exception
     */
    public void pagination(int typePage)
            throws ConnexionDatabaseException, RequeteQueryException {

        do {

            switch (typePage) {

            case 1:

                pageOrdinateur = gestionOrdinateur.findOrdinateurByPage(
                        numeroPage, ligneParPage, "", "", false);

                if (pageOrdinateur.getContenue().isEmpty()) {

                    numeroPage--;
                    pageOrdinateur = gestionOrdinateur.findOrdinateurByPage(
                            numeroPage, ligneParPage, "", "", false);

                }

                affichagePage(typePage);
                break;

            case 2:

                pageEntreprise = gestionEntreprise.findEntreprise();

                if (pageEntreprise.isEmpty()) {

                    numeroPage--;
                    pageEntreprise = gestionEntreprise.findEntreprise();

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
