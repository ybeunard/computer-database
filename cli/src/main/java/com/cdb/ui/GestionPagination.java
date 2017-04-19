package com.cdb.ui;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cdb.model.dto.CompanyDto;
import com.cdb.model.dto.ComputerDto;
import com.cdb.utils.Parse;

/**
 * The Class GestionPagination.
 */
public class GestionPagination {

    /** The rest template. */
    private static RestTemplate restTemplate = new RestTemplate();

    /** The Constant FILTER. */
    private static final String FILTER = "filter";
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

        do {

            switch (typePage) {

            case "computer":

                try {
                    ResponseEntity<List> computers = restTemplate.getForEntity(
                            UserInterpreter.uri + UserInterpreter.uriComputers
                                    + numPage + UserInterpreter.SLASH
                                    + rowByPage,
                            List.class);

                    if (computers.getStatusCode() == HttpStatus.OK) {
                        pageComputer = computers.getBody();
                        displayPage(typePage);
                    }

                } catch (RestClientException restClientException) {

                    System.out.println(
                            "Problem occured when getting computer with page");
                    restClientException.getMessage();

                }
                displayPage(typePage);
                break;

            case "company":

                try {
                    ResponseEntity<List> companies = restTemplate.getForEntity(
                            UserInterpreter.uri + UserInterpreter.uriCompanies
                                    + numPage + UserInterpreter.SLASH
                                    + rowByPage,
                            List.class);
                    if (companies.getStatusCode() == HttpStatus.OK) {
                        pageCompany = companies.getBody();
                        displayPage(typePage);
                    }

                } catch (RestClientException restClientException) {

                    System.out.println(
                            "Problem occured when getting company with page");
                    restClientException.getMessage();

                }
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

        do {

            switch (typePage) {

            case "computer":
                try {
                    ResponseEntity<List> computers = restTemplate.getForEntity(
                            UserInterpreter.uri + UserInterpreter.uriComputers
                                    + numPage + UserInterpreter.SLASH
                                    + rowByPage + UserInterpreter.SLASH + name,
                            List.class);

                    if (computers.getStatusCode() == HttpStatus.OK) {
                        pageComputer = computers.getBody();
                        displayPage(typePage);
                    }

                } catch (RestClientException restClientException) {

                    System.out.println(
                            "Problem occured when getting computer with page and filter");
                    restClientException.getMessage();

                }
                break;

            case "company":

                try {
                    ResponseEntity<List> companies = restTemplate.getForEntity(
                            UserInterpreter.uri + UserInterpreter.uriCompanies
                                    + UserInterpreter.SLASH + FILTER
                                    + UserInterpreter.SLASH + name,
                            List.class);

                    if (companies.getStatusCode() == HttpStatus.OK) {
                        pageCompany = companies.getBody();
                        displayPage(typePage);
                    }

                } catch (RestClientException restClientException) {

                    System.out.println(
                            "Problem occured when getting company with page and filter");
                    restClientException.getMessage();

                }
                break;

            default:

                System.out.println("Type de page inconnue");
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

            System.out.println("Entrez le nombre de lignes souhaitées :");
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

            for (Object ordinateur : pageComputer) {

                System.out.println(ordinateur.toString());

            }

            break;

        case "company":

            for (Object entreprise : pageCompany) {

                System.out.println(entreprise.toString());

            }

            break;

        default:

            System.out.println("Type de page inconnue");
            return;

        }

        System.out.println("\npage numero : " + numPage + " , " + rowByPage
                + " ligne par page \nprecedent taper b\nsuivant taper n\nchanger le nombre de ligne par page tapez p\nsortir tapez n'importe quelle autre touche");

    }

}
