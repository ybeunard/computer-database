package com.cdb.ui;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.ComputerDto;
import com.cdb.utils.Parse;

/**
 * The Class GestionPagination.
 */
public class GestionPagination {

    /** The numero page. */
    private int numPage;

    /** The ligne par page. */
    private int rowByPage;

    /** The page ordinateur. */
    private List<ComputerDto> pageComputer;

    /** The page entreprise. */
    private List<CompanyDto> pageCompany;

    /**
     * Instantiates a new gestion pagination.
     */
    public GestionPagination() {

        this.numPage = 1;
        this.rowByPage = 100;

    }

    /**
     * Pagination.
     *
     * @param typePage
     *            indique si la page est une page entreprise ou ordinateur
     */
    public void paging(String typePage) {

        numPage = 1;
        WebTarget target;

        do {

            switch (typePage) {

            case "computer":

                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL)
                        .path("computer/find").queryParam("numPage", numPage)
                        .queryParam("rowByPage", rowByPage);
                pageComputer = target.request().get()
                        .readEntity(new GenericType<List<ComputerDto>>() {
                        });
                displayPage(typePage);
                break;

            case "company":

                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL)
                        .path("company/find").queryParam("numPage", numPage)
                        .queryParam("rowByPage", rowByPage);
                pageCompany = target.request().get()
                        .readEntity(new GenericType<List<CompanyDto>>() {
                        });
                displayPage(typePage);
                break;

            default:

                System.out.println("Type de page inconnu");
                return;

            }

        } while (changePage());

    }

    /**
     * Pagination.
     *
     * @param typePage
     *            indique si la page est une page entreprise ou ordinateur
     * @param name
     *            the name filter
     */
    public void pagingByName(String typePage, String name) {

        numPage = 1;
        WebTarget target;

        do {

            switch (typePage) {

            case "computer":

                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL)
                        .path("computer/find").queryParam("numPage", numPage)
                        .queryParam("rowByPage", rowByPage)
                        .queryParam("name", name);
                pageComputer = target.request().get()
                        .readEntity(new GenericType<List<ComputerDto>>() {
                        });
                displayPage(typePage);
                break;

            case "company":

                target = UserInterpreter.CLIENT.target(UserInterpreter.BASE_URL)
                        .path("company/find").queryParam("numPage", numPage)
                        .queryParam("rowByPage", rowByPage)
                        .queryParam("name", name);
                pageCompany = target.request().get()
                        .readEntity(new GenericType<List<CompanyDto>>() {
                        });
                displayPage(typePage);
                break;

            default:

                System.out.println("Type de page inconnu");
                return;

            }

        } while (changePage());

    }

    /**
     * Changement page.
     *
     * @return true, if successful
     */
    private boolean changePage() {

        String entry = UserInterpreter.SCANNER.nextLine();

        switch (entry) {

        case "b":

            if (numPage > 1) {

                numPage--;

            }

            return true;

        case "n":

            numPage++;
            return true;

        case "p":

            System.out.println("Entrez le nombre de ligne souhaité :");
            entry = UserInterpreter.SCANNER.nextLine();
            rowByPage = Parse.parseEntier(entry, 100);
            return true;

        default:

            return false;

        }

    }

    /**
     * Affichage page.
     *
     * @param typePage
     *            the type page
     */
    private void displayPage(String typePage) {

        switch (typePage) {

        case "computer":

            for (ComputerDto ordinateur : pageComputer) {

                System.out.println(ordinateur);

            }

            break;

        case "company":

            for (CompanyDto entreprise : pageCompany) {

                System.out.println(entreprise);

            }

            break;

        default:

            System.out.println("Type de page inconnu");
            return;

        }

        System.out.println("\npage numero : " + numPage + " , " + rowByPage
                + " ligne par page \nprecedent taper b\nsuivant taper n\nchanger le nombre de ligne par page taper p\nsortir taper n'importe qu'elle autres touches");

    }

}
